package com.example.appimportarmultiplosarquivos.config;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.appimportarmultiplosarquivos.commons.Constants;
import com.example.appimportarmultiplosarquivos.dal.DaoArquivo;
import com.example.appimportarmultiplosarquivos.model.Arquivo;

@androidx.room.Database(
        entities = { Arquivo.class },
        version = 1,
        exportSchema = false
)
public abstract class Database extends RoomDatabase {

    public abstract DaoArquivo daoArquivo();
    private static volatile Database INSTANCE;

    public static Database getDatabase(Context context) {

        if(INSTANCE == null) {
            synchronized (Database.class) {
                if(INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                      context.getApplicationContext(),
                      Database.class,
                      Constants.DB_NAME
                    ).build();

                }
            }
        }

        return INSTANCE;
    }

    @Override
    public void clearAllTables() {

    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
