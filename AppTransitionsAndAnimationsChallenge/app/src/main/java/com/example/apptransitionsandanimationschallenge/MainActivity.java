package com.example.apptransitionsandanimationschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.apptransitionsandanimationschallenge.databinding.ActivityMainBinding;

public class MainActivity extends Base {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        explodeTransition(this, binding.circle);
        fadeTransition(this, binding.rectangle);
        rotateView(binding.square);
        switchAnimation(binding.android, binding.rectangle, new Intent(this, SecondActivity.class),this);

    }
}