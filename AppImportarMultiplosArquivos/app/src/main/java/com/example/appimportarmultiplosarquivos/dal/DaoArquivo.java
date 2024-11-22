package com.example.appimportarmultiplosarquivos.dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appimportarmultiplosarquivos.model.Arquivo;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DaoArquivo {

    @Insert
    void insert(Arquivo arquivo);

    @Update
    void update(Arquivo arquivo);

    @Delete
    void delete(Arquivo arquivo);

    @Query("SELECT * FROM CadArquivo0 WHERE ID = :id")
    Arquivo getFile(String id);

    @Query("SELECT * FROM CadArquivo0")
    List<Arquivo> obtemArquivos();

}
