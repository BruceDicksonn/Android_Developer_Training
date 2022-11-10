package com.example.apphelloworld;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        try {

            //Log.d("MainActivity", String.valueOf(5/0));
            Log.d("MainActivity", "Happy Birthday to you!");

        } catch (Exception e) {

            Log.e("MainActivity", e.toString());

        }

    }
}