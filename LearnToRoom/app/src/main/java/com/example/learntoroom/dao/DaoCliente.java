package com.example.learntoroom.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.learntoroom.models.Cliente;
import java.util.ArrayList;

<<<<<<< HEAD
/**
 * Quando usamos a abordagem do Room, cada DAO do nosso projeto deverá ser criada como interface.
 * Cada método abstrato deverá possuir uma annotation referente a alguma funcionalidade do SQLite.
 *
 * Por trás dos panos, quando fazemos o build do projeto, o Room cria classes DAL com toda a estrutura
 * já pronta sem que seja preciso implementarmos do zero a lógica da persistência.
 *
 * **/


=======
>>>>>>> 9444553a902fda8c48b3e4d43916de377ee8138a
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
<<<<<<< HEAD
=======

>>>>>>> 9444553a902fda8c48b3e4d43916de377ee8138a
}
