package com.example.recyclerview2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.recyclerview2.databinding.ActivityMainBinding;
import com.example.recyclerview2.listener.RecyclerItemClickListener;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    AdapterPalavra adapter;
    ArrayList<String> palavras = getListColors();

    final int sizeWordList = palavras.size();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.include.toolbar);

        binding.fab.setOnClickListener(addItemInList);

        adapter = new AdapterPalavra(this, palavras);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recycler.setAdapter(adapter);

        /**
         *
         *  Adicionamos um OnItemTouchListener ao RecyclerView desejado,
         *  ou seja, um ouvinte para cada item que disparar o evento de
         *  toque (OnTouchEvent)
         *
         * **/
        binding.recycler.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                binding.recycler,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        alert(view, palavras.get(position));

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        Toast.makeText(MainActivity.this, palavras.get(position), Toast.LENGTH_SHORT).show();

                    }
                }
        ));

    }

    View.OnClickListener addItemInList = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int size = palavras.size();

            palavras.add("Item: " + size);

            /**
             *
             *   faz com que o adapter seja notificado de uma nova inserção
             *   na lista e o renderize de forma dinâmica.
             *
             * **/
            adapter.notifyDataSetChanged();


            /**
             *
             *  A medida que os novos itens são adicionados, a posição
             *  fica fixada sempre ao final da lista.
             *
             * **/
            binding.recycler.smoothScrollToPosition(size);

        }
    };

    public ArrayList<String> getListColors() {

        ArrayList<String> list = new ArrayList<>();

        Field[] fields = Color.class.getFields();

        for(Field f : fields){
            list.add(f.getName());
        }

        return list;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void resetList(){

        palavras.clear(); // limpa a lista interna
        palavras.addAll(getListColors()); // adiciona os valores novamente
        adapter.notifyDataSetChanged(); // notifica a mudança ao adapter/recycler

        /**
         * Perceba que em momento algum tivemos que fazer qualquer
         * mudança a lista interna do adapter. Qualquer tipo de mudança
         * que quisermos fazer, fazemos por fora e apenas notificamos o
         * adapter para que ele fique ciente e atualize seus valores.
         *
         * **/

    }

    public void alert(View view, String msg) {
        Snackbar snackbar = Snackbar.make(view,msg,Snackbar.LENGTH_LONG).setActionTextColor(Color.BLACK);
        snackbar.getView().setBackgroundColor(Color.BLUE);
        snackbar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.refresh){

            resetList();

        }

        return super.onOptionsItemSelected(item);
    }
}