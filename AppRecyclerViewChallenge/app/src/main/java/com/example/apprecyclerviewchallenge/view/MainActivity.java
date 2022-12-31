package com.example.apprecyclerviewchallenge.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.apprecyclerviewchallenge.adapter.AdapterCookingRecipeRecipeItemList;
import com.example.apprecyclerviewchallenge.databinding.ActivityMainBinding;
import com.example.apprecyclerviewchallenge.listener.RecyclerItemClickListener;
import com.example.apprecyclerviewchallenge.model.Recipe;
import com.example.apprecyclerviewchallenge.utils.UtilsRecipes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<String[]> recipeList;
    AdapterCookingRecipeRecipeItemList adapter;
    RecyclerView.LayoutManager layoutManager;
    UtilsRecipes recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initComponents();
        setSupportActionBar(binding.toolbar);


    }

    public ArrayList<String[]> getRecipeList(){

        ArrayList<Recipe> recipeArrayList =  recipes.getAllRecipes();
        ArrayList<String[]> listReturn = new ArrayList<>();

        for(Recipe r : recipeArrayList) {

            listReturn.add(new String[]{
                    r.getTitle(),
                    r.getDescription()
            });

        }

        return listReturn;

    }

    public void alert(String m) {
        Toast.makeText(this,m,Toast.LENGTH_SHORT).show();
    }

    private void initComponents() {

        recipes = new UtilsRecipes(getResources());
        recipeList = getRecipeList();
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter = new AdapterCookingRecipeRecipeItemList(this, recipeList);

        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(adapter);
        binding.recycler.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                binding.recycler,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void OnItemClick(View view, int position) {

                        Intent intent = new Intent(MainActivity.this, ActivityCookingRecipe.class);
                        intent.putExtra("id", position);

                        startActivity(intent);

                    }

                    @Override
                    public void OnLongItemClick(View view, int position) {
                        alert("onLongClick");
                    }
                }
        ));

    }

}