package com.example.learntoroom.repositories.cliente;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.util.Log;

import com.example.learntoroom.dao.DaoCliente;
import com.example.learntoroom.models.Cliente;

import java.util.ArrayList;

/**
 *
 * Ao utilizarmos uma camada de repositório, garantimos uma camada a mais entre a controller e a DAO.
 * Caso use controller, a mesma terá contato direto com o a interface do usuário e por sua vez mandará
 * as entradas para o repositório específico que no fim os tratará e chamará o método responsável de sua DAO.
 *
 *
 *
 * **/
public class OfflineClienteRepository implements ClienteRepository {

    private DaoCliente daoCliente;

    public OfflineClienteRepository(DaoCliente daoCliente){
        this.daoCliente = daoCliente;
    }

    @SuppressLint("Range")
    @Override
    public ArrayList<Cliente> obterClientes() {

        ArrayList<Cliente> list = new ArrayList();

        try {

            Cursor cursor = daoCliente.obterClientes();
            cursor.moveToFirst();

            while(!cursor.isAfterLast()) {

                int unidade = cursor.getInt(cursor.getColumnIndex("unidade"));
                int codigo = cursor.getInt(cursor.getColumnIndex("codigo"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));

                Cliente cLiente = new Cliente(unidade,codigo,nome);

                list.add(cLiente);

                cursor.moveToNext();

            }
            return list;

        } catch (Exception ex) {
            Log.e("obterClientes", ex.getMessage());
        }

        return null;

    }

    @SuppressLint("Range")
    @Override
    public Cliente obterCliente(int unidade, int codigo) {
        Cursor cursor = daoCliente.obterCliente(unidade, codigo);
        while(!cursor.isAfterLast()) {

            int uni = cursor.getInt(cursor.getColumnIndex("unidade"));
            int cod = cursor.getInt(cursor.getColumnIndex("codigo"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));

            return new Cliente(uni, cod, nome);

        }
        return null;
    }

    @Override
    public long inserirCliente(Cliente cliente) {
        return daoCliente.insert(cliente);
    }

    @Override
    public int atualizarCliente(Cliente cliente) {
        return daoCliente.update(cliente);
    }

    @Override
    public boolean deletarCliente(Cliente cliente) {
         try {
             daoCliente.delete(cliente);
             return true;
        } catch (Exception ex) {
             Log.e("deletarCliente", ex.getMessage());
        }
        return false;
    }
}
