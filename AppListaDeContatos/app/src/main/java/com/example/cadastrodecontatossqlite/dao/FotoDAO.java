package com.example.cadastrodecontatossqlite.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cadastrodecontatossqlite.config.DbHelper;
import com.example.cadastrodecontatossqlite.controller.interfaceMethods;
import com.example.cadastrodecontatossqlite.model.Contato;
import com.example.cadastrodecontatossqlite.model.Foto;

import java.util.List;

public class FotoDAO  {

    String table = "fotos";

    SQLiteDatabase write;
    SQLiteDatabase read;

    public FotoDAO(Context context) {
        write = DbHelper.getInstance(context).getWritableDatabase();
        read = DbHelper.getInstance(context).getReadableDatabase();
    }

    public long insert(Foto foto) {

        try {

            ContentValues cv = new ContentValues();
            cv.put("data", foto.getData());

            return write.insert(table, null, cv);

        } catch (Exception e) {
            Log.e("Info_DB", e.getMessage());
        }

        return -1;
    }

    public boolean delete(Foto foto) {
        return false;
    }


    public boolean update(Foto foto) {
        return false;
    }

    @SuppressLint("Range")
    public Foto find(long id){

        String where = "_id = ?";

        try{

            Cursor cursor = read.query(table, null, where, new String[]{String.valueOf(id)}, null, null, null);
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                int _id = cursor.getInt( cursor.getColumnIndex("_id") );
                byte[] blob = cursor.getBlob( cursor.getColumnIndex("data")  );

                Foto foto = new Foto(_id, blob);
                return foto;

            }

        } catch (Exception e) {
            Log.e("Info_DB", e.getMessage());
        }

        return null;
    }


    public List<Foto> findAll() {
        return null;
    }
}
