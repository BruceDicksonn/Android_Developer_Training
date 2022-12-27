package com.example.hellocompat;

import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        if(savedInstanceState != null) {
            textView.setTextColor(savedInstanceState.getInt("color"));
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putInt("color", textView.getCurrentTextColor());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public void onChangeColor(View view) {

//        int start = 0;
//        int limit = colors.size();
//        int randomPosition = (int)(start + Math.random() * (limit-1));
//        int randomColor;
//
//        //Converter HashMap em ArrayList
//        ArrayList<Map.Entry<String,Integer>> randomColors = new ArrayList<Map.Entry<String,Integer>>(colors.entrySet());

        // forma de percorrer um hashMap
//        for(Map.Entry<String,Integer> entry : colors.entrySet()){
//
//            //randomColors.add(entry.getValue());
//            Log.i("Main", String.valueOf(entry.getValue()));
//
//        }

//        randomColor = randomColors.get(randomPosition).getValue();
//        textView.setTextColor(randomColor);

        Random random = new Random();

        // busca o nome de uma cor do array de cores para usarmos como base quando formos pesquisar nos resources.
        String colorName = mColorArray[random.nextInt(20)];

        // captura o ID da cor relacionada a pasta values > colors.xml
        int colorResourceName = getResources().getIdentifier(colorName, "color", getApplicationContext().getPackageName());
        int idStringResource = getResources().getIdentifier("teste","string", getApplicationContext().getPackageName());

        // captura de fato a cor em si, uma vez tendo o ID da mesma, basta passarmos o contexto e o id da cor que podemos obtê-la.
        int colorRes = ContextCompat.getColor(this, colorResourceName);

        textView.setTextColor(colorRes);
        textView.setText(idStringResource);
        textView.setTextSize(30);

    }

    public void initComponents(){
        textView = findViewById(R.id.text);
        button = findViewById(R.id.button_change_color);
    }
}