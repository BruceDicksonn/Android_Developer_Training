package com.example.cadastrodecontatossqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.cadastrodecontatossqlite.config.DbHelper;
import com.example.cadastrodecontatossqlite.model.Contato;

import java.util.List;

public class ContatoDAO {

    String table = "contatos";
    String columns[] = null;
    String where = "";
    String having = "";
    String orderBy = "";

    SQLiteDatabase write;
    SQLiteDatabase read;

    public ContatoDAO(Context context) {
        this.write = DbHelper.getInstance(context).getWritableDatabase();
        this.read = DbHelper.getInstance(context).getReadableDatabase();
    }


    public long insert(Contato contato) {

        try{

            ContentValues cv = new ContentValues();
            cv.put("nome", contato.getNome());
            cv.put("fone", contato.getTelefone());
            cv.put("id_foto", contato.getImage_id());

            return write.insert(table, null, cv);


        } catch(Exception e){
            e.printStackTrace();
        }

        return -1;
    }

    public boolean delete(Contato contato) {

        try{

        } catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Contato contato) {

        try{

        } catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public Cursor select() {

        table = "contatos LEFT JOIN fotos ON contatos.foto_id = fotos._id";
        columns = new String[]{"contatos.id_foto","fotos._id"};

        String sql = "SELECT c.nome, c.fone, f.data FROM contatos c LEFT JOIN fotos f ON c.id_foto = f._id";

        try{

            Cursor cursor = read.rawQuery(sql,null);
            if(cursor != null) {
                return cursor;
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
