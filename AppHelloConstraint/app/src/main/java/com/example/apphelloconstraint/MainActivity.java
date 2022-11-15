package com.example.apphelloconstraint;

<<<<<<< HEAD
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
=======
>>>>>>> c195a3adbbaa8c3047cdc5107c6da23b37c9f913
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
<<<<<<< HEAD

=======
>>>>>>> c195a3adbbaa8c3047cdc5107c6da23b37c9f913
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphelloconstraint.databinding.ActivityMainBinding;

<<<<<<< HEAD
import java.util.Random;

=======
>>>>>>> c195a3adbbaa8c3047cdc5107c6da23b37c9f913
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
<<<<<<< HEAD
        @RequiresApi(api = Build.VERSION_CODES.O)
=======
>>>>>>> c195a3adbbaa8c3047cdc5107c6da23b37c9f913
        @Override
        public void onClick(View view) {

            int currentValue = Integer.parseInt(main.textView.getText().toString())+1;
            main.textView.setText(String.valueOf(currentValue));
<<<<<<< HEAD
            view.setBackgroundColor(Color.parseColor(getRandomRGP()));
            main.textView.setBackgroundColor(Color.parseColor(getRandomRGP()));
            main.textView.setTextColor(Color.parseColor(getRandomRGP()));
        }
    };
    public String getRandomRGP(){

        String r = String.valueOf((int)(new Random().nextInt(99 + 1 - 10) + 10));
        String g = String.valueOf((int)(new Random().nextInt(99 + 1 - 10) + 10));
        String b = String.valueOf((int)(new Random().nextInt(99 + 1 - 10) + 10));

        String x = new String().concat("#").concat(r).concat(g).concat(b);

        Log.d("MainActivity",x);

        return x;

    }
=======

        }
    };
>>>>>>> c195a3adbbaa8c3047cdc5107c6da23b37c9f913

    View.OnClickListener toast = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"Hello, World!",Toast.LENGTH_LONG).show();
        }
    };

}