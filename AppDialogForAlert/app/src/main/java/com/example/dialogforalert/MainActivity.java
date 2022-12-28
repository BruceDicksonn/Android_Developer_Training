package com.example.dialogforalert;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alert(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    public void onClickShowAlert(View view) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);

        alertBuilder.setTitle("Alert");
        alertBuilder.setMessage("Click OK to continue or CANCEL to stop:");
        alertBuilder.setCancelable(false);
        alertBuilder.setIcon(android.R.drawable.ic_dialog_alert);

        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alert("Pressed OK");
            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alert("Pressed Cancel");
                dialogInterface.dismiss();
            }
        });

        alertBuilder.show();

    }
}