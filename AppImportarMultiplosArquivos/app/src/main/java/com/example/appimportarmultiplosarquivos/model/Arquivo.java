package com.example.appimportarmultiplosarquivos.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CadArquivo0")
public class Arquivo {

    public Arquivo(){}

    public Arquivo(String filename, String path, String extension) {
        this.filename = filename;
        this.path = path;
        this.extension = extension;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "filename")
    public String filename;

    @ColumnInfo(name = "path")
    public String path;

    @ColumnInfo(name = "extension")
    public String extension;

}
