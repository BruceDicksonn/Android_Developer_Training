package com.example.learntojobscheduler.jobs.updateInfo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class UpdateInfoWork extends JobIntentService {

    private static final int JOB_ID = UpdateInfoWork.class.hashCode();
    int count = 0;

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, UpdateInfoWork.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        uploadInfo();
        scheduleRepeatingJob();
    }

    boolean uploadInfo(){

        Log.i("UpdateInfoWork", ("Contagem: " + count));
        return true;
    }

    private void scheduleRepeatingJob() {
        // Criar um intent para o trabalho a ser realizado
        Intent workIntent = new Intent(this, UpdateInfoWork.class);

        // Criar um PendingIntent para o trabalho
        PendingIntent pendingIntent = PendingIntent.getService(
                this,
                0,
                workIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        // Configurar o alarme para iniciar após 1 minuto e repetir a cada 1 minuto
        long interval = TimeUnit.SECONDS.toMillis(3); // Repete a cada 3s
        long nextStartTime = System.currentTimeMillis() + interval; // repete após 3s

        // Configurar o AlarmManager para agendar o trabalho em intervalos específicos
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, nextStartTime, interval , pendingIntent);
    }


}
