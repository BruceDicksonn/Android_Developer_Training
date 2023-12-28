package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;

    private ArrayList<Pais> countries = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        countries.add(new Pais(1, "Brasil"));
        countries.add(new Pais(2, "Estados Unidos"));
        countries.add(new Pais(3, "Canadá"));
        countries.add(new Pais(4, "França"));
        countries.add(new Pais(5, "Alemanha"));
        countries.add(new Pais(6, "Japão"));
        countries.add(new Pais(7, "China"));
        countries.add(new Pais(8, "Argentina"));
        countries.add(new Pais(9, "México"));
        countries.add(new Pais(10, "Jamaica"));
        countries.add(new Pais(11, "Inglaterra"));

        CustomAutoCompleteAdapter adapter = new CustomAutoCompleteAdapter( this, android.R.layout.simple_dropdown_item_1line, countries );
        autoCompleteTextView.setAdapter( adapter );

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int codigo = ( (Pais) adapterView.getAdapter().getItem(i) ).getCodigo();
                Toast.makeText(MainActivity.this, codigo + "" , Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }



}