package com.example.recyclerviewchallengeresponsivelayoutsandglobalstrings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.example.recyclerviewchallengeresponsivelayoutsandglobalstrings.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Object> list = new ArrayList<>();
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list.addAll(Arrays.asList(getResources().getStringArray(R.array.words)));

        adapter = new Adapter(this, list);

        int columns   =  getResources().getInteger(R.integer.columns_count);

        binding.recycler.setLayoutManager(new GridLayoutManager(this,columns));
        binding.recycler.setAdapter(adapter);


    }
}