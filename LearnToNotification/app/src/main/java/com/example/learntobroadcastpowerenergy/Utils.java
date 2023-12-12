package com.example.learntobroadcastpowerenergy;
/*
 * Created by Bruce Dickson
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

public class Utils {

    public static boolean checkEcoEnergyMode( Context context ){

        PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if( manager.isPowerSaveMode() ) return true;
        }

        return false;
    }

    public static AlertDialog createDialog(Context context, String title, String content, String positiveButton, DialogInterface.OnClickListener listener) {

            AlertDialog alertDialog = new AlertDialog
                    .Builder(context)
                    .setTitle( title )
                    .setMessage( content )
                    .setCancelable(false)
                    .setPositiveButton(positiveButton, listener)
                    .create();

            return alertDialog;
    }

    public static AlertDialog createDialogEcoEnergyMode(Context context) {
        AlertDialog alertDialog = new AlertDialog
                .Builder(context)
                .setTitle("Economia de energia")
                .setMessage("A economia de energia impacta em alguns recursos, desative-a.")
                .setCancelable(false)
                .setPositiveButton("Ir p/ configurações", (dialogInterface,i) -> {
                    Intent intent = new Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS);
                    context.startActivity(intent);
                })
                .create();

        alertDialog.show();
        return alertDialog;
    }

}
