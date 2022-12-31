package com.example.applevelbatery;

import android.graphics.drawable.LevelListDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.applevelbatery.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    LevelListDrawable listDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listDrawable = (LevelListDrawable) ContextCompat.getDrawable(this, R.drawable.battery);


    }

    public void increaseBattery(View view) {

        int index = listDrawable.getLevel();

        if(index == 7){

            Toast.makeText(this, "Full Battery", Toast.LENGTH_SHORT).show();

        } else {

            index++;
            listDrawable.setLevel(index);
            binding.batteryImage.setImageDrawable(listDrawable.getCurrent());

        }

    }

    public void decreaseBattery(View view) {

        int index = listDrawable.getLevel();

        if(index == 0){

            Toast.makeText(this, "Empty Battery", Toast.LENGTH_SHORT).show();

        } else {

            index--;
            listDrawable.setLevel(index);
            binding.batteryImage.setImageDrawable(listDrawable.getCurrent());

        }
    }
}