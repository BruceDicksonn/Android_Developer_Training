package com.example.learntoroom.db;

import android.content.Context;

import androidx.room.Room;

import com.example.learntoroom.db.config.AppDatabase;

public class DatabaseClient {

    private static AppDatabase db;

    public static AppDatabase getInstance(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Infosuite").build();
        }
        return db;
    }
}
