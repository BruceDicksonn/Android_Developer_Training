package com.example.learntoroom.ui;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.learntoroom.dao.DaoCliente;
import com.example.learntoroom.databinding.ActivityCadastrarClienteBinding;
import com.example.learntoroom.db.DatabaseClient;
import com.example.learntoroom.models.Cliente;
import com.example.learntoroom.repositories.cliente.OfflineClienteRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CadastrarClienteActivity extends AppCompatActivity {

    ActivityCadastrarClienteBinding binding;
    OfflineClienteRepository clienteRepository;
    long rowsAffected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastrarClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        clienteRepository = new OfflineClienteRepository(DatabaseClient.getInstance(this).daoCliente());
        binding.btnSalvar.setOnClickListener((view -> {

            Cliente cliente = new Cliente(
                Integer.parseInt(binding.editUnidade.getText().toString()),
                Integer.parseInt(binding.editCodigo.getText().toString()),
                binding.editNome.getText().toString()
            );

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(()-> {
                rowsAffected = clienteRepository.inserirCliente( cliente );

                if( rowsAffected > 0 ) {
                    finalizarTela(cliente);
                }

            });

        }));


    }

    void finalizarTela(Cliente cliente){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("cliente", cliente);
        setResult(RESULT_OK, intent);
        finish();
    }

    void showMessageSuccess(){
        Toast.makeText(this, "Novo cliente adicionado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    void showMessageError(){
        Toast.makeText(this, "Novo cliente adicionado com sucesso!", Toast.LENGTH_SHORT).show();
    }

}