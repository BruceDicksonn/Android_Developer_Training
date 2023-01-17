package com.example.apprecyclerviewchallenge.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.apprecyclerviewchallenge.R;
import com.example.apprecyclerviewchallenge.adapter.AdapterCookingRecipeRecipeItemList;
import com.example.apprecyclerviewchallenge.databinding.ActivityMainBinding;
import com.example.apprecyclerviewchallenge.listener.RecyclerItemClickListener;
import com.example.apprecyclerviewchallenge.model.Recipe;
import com.example.apprecyclerviewchallenge.utils.UtilsRecipes;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<String[]> recipeList;
    AdapterCookingRecipeRecipeItemList adapter;
    RecyclerView.LayoutManager layoutManager;
    UtilsRecipes recipes;
    LevelListDrawable listDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initComponents();
        setSupportActionBar(binding.toolbar);

        listDrawable = (LevelListDrawable) ContextCompat.getDrawable(this,R.drawable.level_list_dark_mode);

        if(savedInstanceState != null){

            listDrawable.setLevel(savedInstanceState.getInt("level"));

        }

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

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder fromHolder, @NonNull RecyclerView.ViewHolder toHolder) {

                int from  = fromHolder.getAdapterPosition();
                int to    = toHolder.getAdapterPosition();

                Collections.swap(recipeList,from,to);
                adapter.notifyItemMoved(from,to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                recipeList.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }
        });

        helper.attachToRecyclerView(binding.recycler);

    }

    public void changeTheme(MenuItem item){

        int currentIndex = listDrawable.getLevel();

        if(currentIndex == 0) {

            currentIndex++;
            listDrawable.setLevel(currentIndex);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        } else {

            currentIndex--;
            listDrawable.setLevel(currentIndex);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }

        recreate();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.themeMode).setIcon(listDrawable.getCurrent());
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.refresh:

                recipeList.clear();
                recipeList.addAll(getRecipeList());
                adapter.notifyDataSetChanged();

            break;
            case R.id.themeMode:
                changeTheme(item);
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt("level", listDrawable.getLevel());
        super.onSaveInstanceState(outState);
    }
}