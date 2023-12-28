package com.example.learntoroom.ui;

import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.learntoroom.R;
import com.example.learntoroom.databinding.ActivityMainBinding;
import com.example.learntoroom.db.DatabaseClient;
import com.example.learntoroom.models.Cliente;
import com.example.learntoroom.repositories.cliente.OfflineClienteRepository;
import com.example.learntoroom.ui.adapters.AdapterCliente;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Cliente> listaClientes;
    AdapterCliente adapter;
    OfflineClienteRepository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initComponents();

        binding.btnAdd.setOnClickListener((view -> {
            startActivityForResult(new Intent(getApplicationContext(), CadastrarClienteActivity.class), 0);
        }));

    }

    void initComponents(){

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {

                repository = new OfflineClienteRepository(DatabaseClient.getInstance(getApplicationContext()).daoCliente());
                listaClientes = repository.obterClientes();
                preencherLista(listaClientes);

            }
        });

    }

    void preencherLista(ArrayList listaClientes){
        adapter = new AdapterCliente(getApplicationContext(), listaClientes);
        binding.recyclerClientes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerClientes.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0) {
            if(resultCode == RESULT_OK) {
                Cliente cliente = (Cliente) data.getSerializableExtra("cliente");
                if(cliente != null) {
                    listaClientes.add(cliente);
                    adapter.notifyDataSetChanged();
                }
            }
        }

    }
}