package com.example.simpleasynctaskprogressbar;

import android.util.Log;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class AsyncTask extends android.os.AsyncTask<Void,Integer,Void> {

    WeakReference<ProgressBar> progressBarWeakReference;
    Integer[] timer = {10,30,60,80,100};

    public AsyncTask(ProgressBar progressBar) {
        this.progressBarWeakReference = new WeakReference<>(progressBar);
    }

    @Override
    protected void onPreExecute() {

        Log.i("Script","Iniciada");

        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(Void... voids) {


        for(int i = 0; i < timer.length;i++) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(timer[i]);

        }


        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {

        Log.i("Script","Finalizada");

        super.onPostExecute(unused);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBarWeakReference.get().setProgress(values[0]);
        super.onProgressUpdate(values);
    }

}
