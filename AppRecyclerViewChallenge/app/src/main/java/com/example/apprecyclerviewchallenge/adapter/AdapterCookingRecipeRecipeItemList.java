package com.example.apprecyclerviewchallenge.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apprecyclerviewchallenge.R;
import com.example.apprecyclerviewchallenge.model.Recipe;

import java.util.ArrayList;

public class AdapterCookingRecipeRecipeItemList extends RecyclerView.Adapter<AdapterCookingRecipeRecipeItemList.ViewHolder> {

    Context context;
    ArrayList <String[]> mListRecipes;

    public AdapterCookingRecipeRecipeItemList(Context context, ArrayList<String[]> mListRecipes) {
        this.context = context;
        this.mListRecipes = mListRecipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_cooking_recipe_item_list, viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        String title = mListRecipes.get(i)[0];
        String description = mListRecipes.get(i)[1];

        viewHolder.titleRecipe.setText(title);
        viewHolder.descriptionRecipe.setText(description);

    }

    @Override
    public int getItemCount() {
        return mListRecipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleRecipe;
        TextView descriptionRecipe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleRecipe = itemView.findViewById(R.id.titleRecipeItem);
            descriptionRecipe = itemView.findViewById(R.id.descriptionRecipeItem);

        }

    }
}