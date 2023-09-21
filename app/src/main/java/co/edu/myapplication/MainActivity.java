package co.edu.myapplication;

import static android.Manifest.permission.CAMERA;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;


import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 100;
    private Button checkPermissions;
    private Button requestPermissions;
    private TextView tvCamera;
    private TextView tvDactilar;
    private String[] permissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        begin();
        checkPermissions.setOnClickListener(this::verificarPermisos);
        requestPermissions.setOnClickListener(this::solicitarPermisos);

        permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.USE_BIOMETRIC
        };
    }

    private boolean checkHasPermissions() {
        for (String p : permissions) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), p) != PackageManager.PERMISSION_GRANTED)
                ;
            return false;
        }
        return true;
    }

    private void verificarPermisos() {
        for (String p : permissions) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), p) != PackageManager.PERMISSION_GRANTED)
                ;
        }
    }

    private void verificarPermisos(View view) {
        int statusPermisoCam = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int statusPermisoBio = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.USE_BIOMETRIC);
        tvCamera.setText("Estatus del Permiso de la Cámara:" + statusPermisoCam);
        tvDactilar.setText("Estatus del Permiso de la Huella:" + statusPermisoBio);
        requestPermissions.setEnabled(true);
    }

    public void solicitarPermisos(View view) {
        if ((ActivityCompat.checkSelfPermission(getApplicationContext(), CAMERA) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{CAMERA}, REQUEST_CODE);

        }
    }

    private void begin() {
        tvCamera = findViewById(R.id.tvCamera);
        tvDactilar = findViewById(R.id.tvBiometric);
        checkPermissions = findViewById(R.id.btnCheckPermissions);
        requestPermissions = findViewById(R.id.btnRequestPermissions);
        //Deshabilitar el boton request
        requestPermissions.setEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if ((grantResults[0] != PackageManager.PERMISSION_GRANTED)
                    || (grantResults[2] != PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(this, "Negaste los permisos, dirigete a configuración", Toast.LENGTH_SHORT).show();
            }
        }
    }
}