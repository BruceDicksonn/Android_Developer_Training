package com.example.headsetbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class HeadsetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if(action != null) {

            switch (action){
                case Intent.ACTION_HEADSET_PLUG:
                    Toast.makeText(context,"Fone plugado", Toast.LENGTH_LONG).show();
                break;
                case Intent.ACTION_BATTERY_LOW:
                    Toast.makeText(context,"Bateria baixa!", Toast.LENGTH_LONG).show();
                break;
            }

        }

    }
}