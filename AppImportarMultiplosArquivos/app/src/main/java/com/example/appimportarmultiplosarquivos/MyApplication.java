package com.example.appimportarmultiplosarquivos;

import android.app.Application;

import com.example.appimportarmultiplosarquivos.config.Database;

public class MyApplication extends Application {

    public static Database database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Database.getDatabase(this);
    }
}
