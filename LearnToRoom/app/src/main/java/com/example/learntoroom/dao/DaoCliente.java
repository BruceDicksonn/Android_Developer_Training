package com.example.learntoroom.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.learntoroom.models.Cliente;
import java.util.ArrayList;

@Dao
public interface DaoCliente {

    @Insert
    long insert(Cliente cliente);

    @Update
    int update(Cliente cliente);

    @Delete
    void delete(Cliente cliente);

    @Query("Select * from CadCli0")
    Cursor obterClientes();

    @Query("Select * from CadCli0 where unidade = :unidade and codigo = :codigo")
    Cursor obterCliente(int unidade, int codigo);

}
