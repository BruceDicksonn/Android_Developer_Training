package com.example.appsimpleasynctask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask <Void, String, String> {

    WeakReference<Context> context;
    WeakReference<TextView> textViewWeakReference;

    public SimpleAsyncTask(Context context,TextView tv) {

        this.context = new WeakReference<>(context);
        this.textViewWeakReference = new WeakReference<>(tv);

    }

    @Override
    protected void onPreExecute() {

        Log.i("Script","onPreExecute()");
        super.onPreExecute();

    }

    @SuppressLint("DefaultLocale")
    @Override
    protected String doInBackground(Void... voids) {

        Log.i("Script","doInBackground()");

        Random random = new Random();
        int n = random.nextInt(11);
        int timer = n * 1000;

        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        publishProgress(String.format("Awake at least after sleeping for %d miliseconds!", timer));

        return String.format("Awake at least after sleeping for %d miliseconds!", timer);
    }

    @Override
    protected void onProgressUpdate(String... values) {

        Log.i("Script","onProgressUpdate()");
        textViewWeakReference.get().setText(values[0]);

        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(String s) {

        Log.i("Script","onPostExecute()");
        //textViewWeakReference.get().setText(s);
    }
}
