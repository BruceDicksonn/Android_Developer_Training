package com.example.testesolicitarpermissoes;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String[] STORAGE_PERMISSIONS = new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE  };
    String[] LOCATION_PERMISSIONS = new String[]{ Manifest.permission.ACCESS_FINE_LOCATION };
    String[] DOCUMENT_PERMISSIONS = new String[]{ Manifest.permission.MANAGE_DOCUMENTS };

    public static String[] ALL_PERMISSIONS = {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION ,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.MANAGE_DOCUMENTS,
    };

    int REQUEST_CODE = 100;

    private ActivityResultLauncher<String[]> requestPermissionLauncher;

    AlertDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ALL_PERMISSIONS = new String[] { Manifest.permission.ACCESS_BACKGROUND_LOCATION };

        dialog = createPermissionsDialog();


        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {

        });

        requestPermissionLauncher.launch(ALL_PERMISSIONS);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onResume() {

        //checkStoragePermissions(this);
        //checkDocumentsPermissions(this);
//        boolean checkLocationPerms = checkGpsPermissions(this);

        boolean checkLocationPerms = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if(checkLocationPerms) {
            Toast.makeText(this, "Permissões de localização concedidas", Toast.LENGTH_LONG).show();
        }

        super.onResume();
    }

    @Override
    protected void onPause() {

        Log.i("onPause", "Pausei");

        super.onPause();
    }

    void checkStoragePermissions(Activity activity) {

        Context context = activity.getApplicationContext();

        int permission  = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission2 = ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);

        if(permission != PackageManager.PERMISSION_GRANTED || permission2 != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity, STORAGE_PERMISSIONS, REQUEST_CODE);

        }

    }

    void checkDocumentsPermissions(Activity activity) {

        int permission = ActivityCompat.checkSelfPermission( getApplicationContext(), Manifest.permission.MANAGE_DOCUMENTS );

        if(permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    DOCUMENT_PERMISSIONS,
                    200

            );
        }

    }

    boolean checkGpsPermissions(Activity activity) {

        Context context = activity.getApplicationContext();

        int permission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        int permission2 = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_BACKGROUND_LOCATION);

        boolean enabled = ((permission == PackageManager.PERMISSION_GRANTED) && (permission2 == PackageManager.PERMISSION_GRANTED));

        if(!enabled) {
            dialog.show();
            return false;
        }


        return true;
    }

    AlertDialog createPermissionsDialog() {

        String passos = "\n 1 - Clique em permissões; \n\n 2 - Clique em Local; \n\n 3 - Selecione a opção Permitir o tempo todo; \n\n";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção");
        builder.setMessage("É comumente necessário que você permita a captura de localização o tempo todo para que possamos registrar os pontos de gps para o cálculo da ajuda de custo da kilometragem: \n" + passos);
        builder.setPositiveButton("Abrir configurações", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

                dialogInterface.dismiss();

            }
        });


        return builder.create();

    }
}