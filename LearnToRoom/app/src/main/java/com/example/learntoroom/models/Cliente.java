package com.example.learntoroom.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

<<<<<<< HEAD

/**
 * Cada model do seu projeto deverá ser anotada como Entity e deverá obrigatoriamente
 * possuir o seguinte valor:
 *
 * @tablename -> representa o nome da tabela associada a essa model;
 * @primaryKeys -> obrigatório em casos de tabelas com duas ou mais colunas pk;
 * @foreignKey -> em casos de relacionamentos, para definir chaves estrangeiras,
 * devemos passar os seguintes valores:
 *      @ForeignKey( entity = User.class,             -> Qual a classe(Entity) a qual a table atual possui relação;
 *                   parentColumns = "id",            -> Qual coluna da table User representa a fk da table atual;
 *                   childColumns = "userId",         -> Qual a coluna que é a fk da table atual;
 *                   onDelete = ForeignKey.CASCADE) ) -> Importante definirmos delete e update em modo cascata;
 *
 * **/

=======
>>>>>>> 9444553a902fda8c48b3e4d43916de377ee8138a
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
