package com.example.learntoroom.db;

import android.content.Context;

import androidx.room.Room;

<<<<<<< HEAD
import com.example.learntoroom.db.config.AppDatabase;

=======
>>>>>>> 9444553a902fda8c48b3e4d43916de377ee8138a
public class DatabaseClient {

    private static AppDatabase db;

    public static AppDatabase getInstance(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Infosuite").build();
        }
        return db;
    }
}
