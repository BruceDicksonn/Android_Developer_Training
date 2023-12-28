package com.example.learntoroom.db.config;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.learntoroom.dao.DaoCliente;
import com.example.learntoroom.models.Cliente;

/**
 *
 * Esse é um arquivo obrigatório, pois é o arquivo lido pelo room que define toda a estrutura do banco
 * de dados, suas tabelas, versões e etc...
 *
 * Nele deveremos fornecer métodos abstratos que darão acesso a cada DAO do projeto para que os repositories
 * possam fazer uso.
 *
 * **/
@Database(entities = {Cliente.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoCliente daoCliente();
}
