package com.example.learntobroadcastpowerenergy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/*
 * Created by Bruce Dickson
 */


/**
 *
 * Esse é um broadcast criado somente com o intuito de escutar as acoes do usuario
 * referente a economia de energia, nele eu aplico minha NotificationHelper para que seja
 * disparado uma notificacao do usuario sinalizando-o para que possa desativar.
 *
 * **/
public class PowerSaveModeReceiver extends BroadcastReceiver {

    AlertDialog alertDialog;
    NotificationHelper helper;

    @Override
    public void onReceive(Context context, Intent intent) {

        PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        helper = new NotificationHelper(context);

        if(manager != null && manager.isPowerSaveMode()){

            helper.showNotification( NotificationHelper.EnumNotifications.ECO_ENERGY_MODE );
            showDialog(context);
            return;

        } else {

            helper.cancelNotification( NotificationHelper.EnumNotifications.ECO_ENERGY_MODE );
            hideDialog();
        }

    }

    void showDialog(Context context) {

        MyNotification notification = helper.searchNotification( NotificationHelper.EnumNotifications.ECO_ENERGY_MODE );

        if(alertDialog == null && notification != null) {

            alertDialog = Utils.createDialog(
                    context,
                    notification.getContentTitle(),
                    notification.getContentTitle(),
                    "Ir p/ configurações",
                    (dialogInterface,i) -> {
                        Intent intent = new Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS);
                        context.startActivity(intent);
                    }
            );

            alertDialog.show();
            return;

        }

        alertDialog.show();
    }

    void hideDialog() {
        if(alertDialog != null) {
            alertDialog.hide();
        }

    }

}
