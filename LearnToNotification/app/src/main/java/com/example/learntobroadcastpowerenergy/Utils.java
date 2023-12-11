package com.example.learntobroadcastpowerenergy;
/*
 * Created by Bruce Dickson
 */

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.PowerManager;
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

}
