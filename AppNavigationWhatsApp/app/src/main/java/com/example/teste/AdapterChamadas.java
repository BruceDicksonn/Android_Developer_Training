package com.example.teste;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterChamadas extends RecyclerView.Adapter<AdapterChamadas.ViewHolderChamadas> {

    Context context;
    ArrayList<String[]> contatos;

    public AdapterChamadas(Context context, ArrayList<String[]> lista){
        this.context  = context;
        this.contatos = lista;
    }

    @NonNull
    @Override
    public ViewHolderChamadas onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_adapter_chamadas, parent, false);
        return new ViewHolderChamadas(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderChamadas viewHolderChamadas, int position) {

        String[] contato = contatos.get(position);

        viewHolderChamadas.nomeContato.setText(contato[0]);
        viewHolderChamadas.numeroContato.setText(contato[1]);
        viewHolderChamadas.iconeContato.setImageResource(R.drawable.ic_person);

    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

    public class ViewHolderChamadas extends RecyclerView.ViewHolder {

        TextView nomeContato;
        TextView numeroContato;
        ImageView iconeContato;

        public ViewHolderChamadas(@NonNull View itemView) {
            super(itemView);

            nomeContato = itemView.findViewById(R.id.nomeContato);
            numeroContato = itemView.findViewById(R.id.numeroContato);
            iconeContato = itemView.findViewById(R.id.fotoContato);

        }

    }






}