package com.example.learntoroom.ui.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.learntoroom.R;
import com.example.learntoroom.models.Cliente;

import java.util.ArrayList;

public class AdapterCliente extends RecyclerView.Adapter<AdapterCliente.ViewHolder> {

    Context context;
    ArrayList<Cliente> listaClientes;

    public AdapterCliente(Context context, ArrayList<Cliente> listaClientes) {
        this.context = context;
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_cliente, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Cliente cliente = listaClientes.get(i);

        viewHolder.nome.setText( cliente.getNome() );
        viewHolder.unidade.setText( String.valueOf(cliente.getUnidade()) + " - " );
        viewHolder.codigo.setText( String.valueOf(cliente.getCodigo()) );

    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nome,unidade,codigo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nome);
            unidade = itemView.findViewById(R.id.unidade);
            codigo =  itemView.findViewById(R.id.codigo);

        }
    }
}
