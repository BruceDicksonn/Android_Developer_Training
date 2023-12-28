package com.example.learntoroom.repositories.cliente;

import com.example.learntoroom.models.Cliente;
import java.util.ArrayList;

public interface ClienteRepository {

    ArrayList<Cliente> obterClientes();
    Cliente obterCliente(int unidade, int codigo);
    long inserirCliente(Cliente cliente);
    int atualizarCliente(Cliente cliente);
    boolean deletarCliente(Cliente cliente);

}
