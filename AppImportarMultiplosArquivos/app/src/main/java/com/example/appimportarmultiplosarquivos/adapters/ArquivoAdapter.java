package com.example.appimportarmultiplosarquivos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appimportarmultiplosarquivos.R;
import com.example.appimportarmultiplosarquivos.commons.ArquivoListeners;
import com.example.appimportarmultiplosarquivos.dal.DaoArquivo;
import com.example.appimportarmultiplosarquivos.model.Arquivo;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArquivoAdapter extends RecyclerView.Adapter<ArquivoAdapter.ViewHolder> {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Context context;
    List<Arquivo> adapterList;
    DaoArquivo daoArquivo;
    ArquivoListeners listeners;

    public ArquivoAdapter(Context context, List<Arquivo> adapterList, DaoArquivo daoArquivo, ArquivoListeners listeners) {
        this.context = context;
        this.adapterList = adapterList;
        this.daoArquivo = daoArquivo;
        this.listeners = listeners;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_arquivo_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Arquivo arquivo = adapterList.get(position);

        holder.filename.setText( arquivo.filename );

        holder.iconVisualize.setOnClickListener((view -> { listeners.visualizarArquivo(position); }));
        holder.iconDelete.setOnClickListener((view -> { listeners.deletarArquivo(arquivo); }));

    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }

    public List<Arquivo> getAdapterList() {
        return adapterList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public  TextView  filename;
        private ImageView iconVisualize;
        private ImageView iconDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            filename = itemView.findViewById(R.id.filename);
            iconVisualize = itemView.findViewById(R.id.visualize);
            iconDelete = itemView.findViewById(R.id.delete);

        }
    }

}
