package com.example.learntoroom.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.learntoroom.dao.DaoCliente;
import com.example.learntoroom.models.Cliente;

@Database(entities = {Cliente.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoCliente daoCliente();
}
