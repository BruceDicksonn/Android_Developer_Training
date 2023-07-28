package com.example.cadastrodecontatossqlite.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cadastrodecontatossqlite.R;
import com.example.cadastrodecontatossqlite.model.Contato;
import java.util.List;

public class AdapterContato extends RecyclerView.Adapter<AdapterContato.ViewHolder> {

    List<Contato> listaContato;

    public AdapterContato(List<Contato> listaContato) {
        this.listaContato = listaContato;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_contato, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Contato contato = listaContato.get(i);

        viewHolder.foto.setImageBitmap(contato.getFoto());
        viewHolder.nome.setText(contato.getNome());
        viewHolder.fone.setText(contato.getTelefone());

    }

    @Override
    public int getItemCount() {
        return listaContato.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView foto;
        TextView nome,fone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foto = itemView.findViewById(R.id.fotoAdapter);
            nome = itemView.findViewById(R.id.nomeAdapter);
            fone = itemView.findViewById(R.id.telefoneAdapter);

        }
    }

    public void setListaContato(List<Contato> listaContato) {
        this.listaContato = listaContato;
        notifyDataSetChanged();
    }
}
