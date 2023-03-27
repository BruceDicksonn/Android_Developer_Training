package com.example.powerreceiver;

import android.app.ApplicationErrorReport;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.BatteryState;
import android.os.BatteryManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {

        String intentAction = intent.getAction();

        if(intentAction != null){

            String toastMessage = "Ação de intent desconhecida.";

            switch (intentAction) {

                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Carregamento inicializado!";
                break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Carregamento finalizado!";
                break;
                case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                    toastMessage = "Modo avião ativado.";
                break;
                case Intent.ACTION_BATTERY_LOW:

                    BatteryManager manager = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
                    int level = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

                    Log.i("BatteryLevel", String.valueOf(level));

                    toastMessage = "Nível baixo de bateria.";

                break;
                case ACTION_CUSTOM_BROADCAST:

                    int number = intent.getExtras().getInt("number");
                    int square = (int) Math.pow(number,2);
                    toastMessage = "Custom Broadcast Received. \n Square of the random number: " + number + " / " + square;

                break;

            }

            Toast.makeText(context,toastMessage,Toast.LENGTH_LONG).show();

        }

    }

}