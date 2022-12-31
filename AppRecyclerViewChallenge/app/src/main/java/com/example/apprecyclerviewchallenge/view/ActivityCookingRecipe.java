package com.example.apprecyclerviewchallenge.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.apprecyclerviewchallenge.R;
import com.example.apprecyclerviewchallenge.adapter.AdapterCookingRecipe;
import com.example.apprecyclerviewchallenge.databinding.ActivityCookingRecipeBinding;
import com.example.apprecyclerviewchallenge.model.Recipe;
import com.example.apprecyclerviewchallenge.utils.UtilsRecipes;

public class ActivityCookingRecipe extends AppCompatActivity {

    ActivityCookingRecipeBinding binding;
    AdapterCookingRecipe adapter;
    RecyclerView.LayoutManager layoutManager;
    UtilsRecipes utilsRecipes;
    Recipe recipe;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCookingRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            index = extras.getInt("id");
        }

        initComponents();
        getSupportActionBar().setTitle(recipe.getTitle());

    }

    private void initComponents() {

        utilsRecipes = new UtilsRecipes(getResources());
        recipe = utilsRecipes.getRecipeToIndex(index);

        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter = new AdapterCookingRecipe(this, recipe);

        binding.recyclerCookingRecipe.setLayoutManager(layoutManager);
        binding.recyclerCookingRecipe.setAdapter(adapter);

    }

}