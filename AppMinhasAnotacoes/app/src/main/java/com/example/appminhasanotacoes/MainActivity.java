package com.example.appminhasanotacoes;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.appminhasanotacoes.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AnotacaoPreferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferencias = new AnotacaoPreferencias(this);
        binding.contentMain.editAnotacao.setText(preferencias.recuperarAnotacao());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.contentMain.editAnotacao.getText().toString().isEmpty()){

                    Snackbar.make(view, "Preencha a anotação", Snackbar.LENGTH_LONG)
                            .show();

                } else {

                    String anotacao = binding.contentMain.editAnotacao.getText().toString();
                    preferencias.salvarAnotacao(anotacao);

                    Snackbar.make(view, "Anotação salva com sucesso!", Snackbar.LENGTH_LONG)
                            .show();

                }

            }
        });
    }
}