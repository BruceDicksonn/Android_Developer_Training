package com.example.learntobroadcastpowerenergy;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    PowerSaveModeReceiver broadcast = new PowerSaveModeReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED");
        registerReceiver(broadcast, filter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.checkEcoEnergyMode(this)){
            NotificationHelper helper = new NotificationHelper(this);
            helper.showNotification(NotificationHelper.EnumNotifications.ECO_ENERGY_MODE);
            Utils.createDialogEcoEnergyMode(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(broadcast != null) {
            unregisterReceiver(broadcast);
            NotificationHelper.clearAllNotifications(this); // limpa todas as notifications ao encerrar o app
        }
    }

}