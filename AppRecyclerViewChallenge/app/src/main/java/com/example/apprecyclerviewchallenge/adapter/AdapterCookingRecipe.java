package com.example.apprecyclerviewchallenge.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apprecyclerviewchallenge.R;
import com.example.apprecyclerviewchallenge.model.Recipe;

public class AdapterCookingRecipe extends RecyclerView.Adapter<AdapterCookingRecipe.ViewHolder> {

    Context context;
    Recipe mRecipe;

    public AdapterCookingRecipe(Context context, Recipe mRecipe) {
        this.context = context;
        this.mRecipe = mRecipe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_cooking_recipe, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.image.setImageBitmap(mRecipe.getImage());
        viewHolder.ingredients.setText(concatArrayString(mRecipe.getIngredients()));
        viewHolder.procedures.setText(concatArrayString(mRecipe.getProcedures()));

    }

    public String concatArrayString(String[] arrayString){

        String returned = "";

        for(String string : mRecipe.getIngredients()){
            returned += string.contains("\n") ? "- " + string : "- " + string + "\n";
        }

        return returned;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView ingredients;
        TextView procedures;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageRecipe);
            ingredients = itemView.findViewById(R.id.ingredientsRecipe);
            procedures = itemView.findViewById(R.id.proceduresRecipe);

        }

    }
}
