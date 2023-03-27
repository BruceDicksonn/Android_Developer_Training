package com.example.mymovies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymovies.R;
import com.example.mymovies.model.Serie;
import com.example.mymovies.utils.Utils;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;

import java.util.ArrayList;

public class AdapterSerie extends RecyclerView.Adapter<AdapterSerie.AdapterItemHolder> {

    Context context;
    ArrayList<Serie> list;

    public AdapterSerie(Context context, ArrayList<Serie> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item,parent,false);
        return new AdapterItemHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemHolder holder, int position) {

        Serie item = list.get(position);

        /* Criados para dar um efeito suave de loading */
        Shimmer shimmer = Utils.createNewShimmer(context);
        ShimmerDrawable drawable = new ShimmerDrawable();
        drawable.setShimmer(shimmer);

        Glide.with(context)
                .load(item.getPoster_path())
                .placeholder(drawable)
                .into(holder.posterItem);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AdapterItemHolder extends RecyclerView.ViewHolder {

        ImageView posterItem;

        public AdapterItemHolder(@NonNull View itemView) {
            super(itemView);

            posterItem = itemView.findViewById(R.id.posterItem);

        }
    }

}
