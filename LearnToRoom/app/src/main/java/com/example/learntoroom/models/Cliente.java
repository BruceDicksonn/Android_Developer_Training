package com.example.learntoroom.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "CadCli0", primaryKeys = {"unidade","codigo"})
public class Cliente implements Serializable {

    int unidade = 0;
    int codigo = 0;

    String nome = "";

    public Cliente(int unidade, int codigo, String nome) {
        this.unidade = unidade;
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getUnidade() {
        return unidade;
    }

    public void setUnidade(int unidade) {
        this.unidade = unidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
