package com.example.recyclerview2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class  AdapterPalavra extends RecyclerView.Adapter<AdapterPalavra.ViewHolder> {

    Context context;
    ArrayList<String> mPalavras;

    public AdapterPalavra(Context context, ArrayList<String> palavras){

        this.context = context;
        this.mPalavras = palavras;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_palavra, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        String palavra = mPalavras.get(position);
        viewHolder.palavra.setText(palavra);

    }

    @Override
    public int getItemCount() {
        return mPalavras.size();
    }

    public void alert(String msg) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView palavra;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            palavra = itemView.findViewById(R.id.textAdapter);

            /**
             *  Forma de setar um OnClickLister para cada item da lista:
             *
             *          itemView.setOnClickListener(this);
             *
             * **/
        }

        @Override
        public void onClick(View view) {
            alert(mPalavras.get(getLayoutPosition()));
        }
    }
}
