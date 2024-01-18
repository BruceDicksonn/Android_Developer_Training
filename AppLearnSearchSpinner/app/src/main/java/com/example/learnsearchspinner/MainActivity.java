package com.example.learnsearchspinner;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.learnsearchspinner.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Motivo> list = new ArrayList<>();
    ActivityMainBinding binding;
    Dialog dialog;
    ArrayAdapter<Motivo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fillArrayList();
        populateSpinner();

    }

    void fillArrayList() {
        list.add(new Motivo(1, "Usuario sem foto anexada ao perfil",1));
        list.add(new Motivo(2, "Email ou senha incorretos",2));
        list.add(new Motivo(3, "Cliente é fã do Los Hermanos e isso é inaceitável",3));
        list.add(new Motivo(4, "Better call Saul é a série mais injustiçada de todos os tempos",4));
        list.add(new Motivo(5, "Sem link, sem like",5));
        list.add(new Motivo(6, "Estou sem ideias para isso aqui",0));
    }

    void populateSpinner() {

        /**
         *
         * O grande segredo desse search é o TextWatcher que adicionamos ao editText
         * que recebe o filtro.
         *
         * Como temos um listView que possui por si só um adapter podemos usar seus metodos
         * internos de filtro para exibirmos a lista filtrada;
         *
         * **/

        binding.textSpinner.setOnClickListener((view) -> {

            View layout = LayoutInflater.from(this).inflate(R.layout.layout_searchable_spinner, null);

            dialog = new Dialog(this);
            dialog.setContentView(layout);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            dialog.show();

            EditText search = layout.findViewById(R.id.search);
            ListView listView = layout.findViewById(R.id.list);

            adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    list );

            listView.setAdapter(adapter);
            search.addTextChangedListener(watcher);

            listView.setOnItemClickListener((adapterView, view1, position, l) -> {
                binding.textSpinner.setText( adapter.getItem(position).Descricao );
                dialog.dismiss();
            });

        });

    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            adapter.getFilter().filter(charSequence);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public class Motivo {

        /**
         * Id : 46
         * Descricao : O cliente não estava no endereço
         * Servico : 0
         */

        private int Id;
        private String Descricao;
        private int Servico;

        public Motivo(int id, String descricao, int servico) {
            Id = id;
            Descricao = descricao;
            Servico = servico;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getDescricao() {
            return Descricao;
        }

        public void setDescricao(String Descricao) {
            this.Descricao = Descricao;
        }

        public int getServico() {
            return Servico;
        }

        public void setServico(int Servico) {
            this.Servico = Servico;
        }

        @Override
        public String toString() {
            return String.format("%d - %s", getId(), getDescricao());
        }
    }

}