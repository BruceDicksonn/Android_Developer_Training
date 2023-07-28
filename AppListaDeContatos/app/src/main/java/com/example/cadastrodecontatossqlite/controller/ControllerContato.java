package com.example.cadastrodecontatossqlite.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.cadastrodecontatossqlite.dao.ContatoDAO;
import com.example.cadastrodecontatossqlite.model.Contato;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ControllerContato implements interfaceMethods{

    ContatoDAO dao;

    public ControllerContato(Context context) {
        this.dao = new ContatoDAO(context);
    }

    @Override
    public boolean insert(Contato contato) {

        if(contato != null) {
            if(dao.insert(contato) != -1) return true;
        }

        return false;

    }

    @Override
    public boolean delete(Contato contato) {

        try {

        } catch (Exception e) {
            Log.e("Info_DB", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Contato contato) {

        try {

        } catch (Exception e) {
            Log.e("Info_DB", e.getMessage());
        }

        return false;
    }

    @SuppressLint("Range")
    @Override
    public List<Contato> findAll() {

        List<Contato> list = new ArrayList<>();
        Cursor cursor = dao.select();
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){

            String nome = cursor.getString( cursor.getColumnIndex("nome") );
            String fone = cursor.getString( cursor.getColumnIndex("fone") );
            byte[] blob = cursor.getBlob( cursor.getColumnIndex("data")   );

            Contato contato = new Contato(nome,fone,blob);
            list.add(contato);

            cursor.moveToNext();


        }

        return list;
    }


}
