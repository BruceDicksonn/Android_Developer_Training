package com.example.apphelloconstraint;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphelloconstraint.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(main.getRoot());

        main.btnCount.setOnClickListener(contar);
        main.btnZero.setOnClickListener(zerar);
        main.btnToast.setOnClickListener(toast);

    }

    View.OnClickListener zerar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            main.textView.setText("0");
        }
    };

    View.OnClickListener contar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int currentValue = Integer.parseInt(main.textView.getText().toString())+1;
            main.textView.setText(String.valueOf(currentValue));

        }
    };

    View.OnClickListener toast = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"Hello, World!",Toast.LENGTH_LONG).show();
        }
    };

}