package com.example.cadastrodecontatossqlite.config;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

public class ControleDatabase {

    private ArrayList<String[]> sql;
    int oldVersion, newVersion;

    public ControleDatabase(int oldVersion, int newVersion) {
        this.oldVersion = oldVersion;
        this.newVersion = newVersion;

        // a lista interna é iniciada com o limite da versão
        sql = new ArrayList<>(newVersion + 1);

        // inicializa a lista com todos os valores nulos
        for(int i=1; i < newVersion; i++){sql.add(null);};

        // preenche a lista
        initSqlUpdate();
    }

    public void execUpdate(SQLiteDatabase db){

    }

    public void execSQL(SQLiteDatabase db, String comando){
        try {
            db.execSQL(comando);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSqlUpdate() {
        /**
         *
         * Nesse método você preenche a lista interna de comandos  sql que a classe tem.
         * A cada atualização, crie um novo item com um novo index. Esse novo index deverá
         * ser igual a versão do banco, ou seja, se o banco está na primeira versão e você decidiu
         * adicionar mais tabelas, crie um elemento na lista interna com o index 2 e mude a constante
         * DB_VERSION na classe DbHelper
         *
         * **/
    }

    public void execCreate(SQLiteDatabase db)
    {
        try {

            // cada comando do array de strings é executado um por um
            for(String comando : sqlCreator()) {
                db.execSQL(comando);
            }

        } catch (Exception ex) {
            Log.e("Info_DB", "Erro ao criar banco de dados: " + ex.getMessage());
        }
    }


    public String[] sqlCreator() {
        return new String[] {
                "CREATE TABLE fotos(_id INTEGER PRIMARY KEY AUTOINCREMENT, data BLOB);",

                "CREATE TABLE contatos(" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " nome VARCHAR(80) NOT NULL," +
                        " fone VARCHAR(20) NOT NULL," +
                        " id_foto INTEGER, " +
                        "FOREIGN KEY(id_foto) REFERENCES fotos(_id) ON DELETE RESTRICT" +
                ");"
        };
    }

}
