package com.example.cadastrodecontatossqlite.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.cadastrodecontatossqlite.utils.Utils;

import java.io.Serializable;

public class Contato implements Serializable {

    Bitmap foto;
    String nome;
    String telefone;
    int image_id;

    public Contato(){}

    public Contato(String nome, String telefone, byte[] blob){
        this.nome = nome;
        this.telefone = telefone;
        this.foto = BitmapFactory.decodeByteArray(blob,0, blob.length);
    }

    public Contato(String nome, String telefone, Bitmap bitmap){

    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public void setFoto(byte[] byteArray) {
        this.foto = Utils.convertByteArrayToBitmap(byteArray);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }
}
