package com.example.powerreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CustomReceiver customReceiver = new CustomReceiver();
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);

        registerReceiver(customReceiver,filter);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(customReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));

    }

    @Override
    protected void onDestroy() {

        LocalBroadcastManager.getInstance(this).unregisterReceiver(customReceiver); // desativando broadcast local
        unregisterReceiver(customReceiver); // desativando broadcast dinâmico

        super.onDestroy();

    }

    public void sendCustomBroadcast(View view) {

        Random random = new Random();

        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        customBroadcastIntent.putExtra("number", random.nextInt(20));

        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);

    }
}