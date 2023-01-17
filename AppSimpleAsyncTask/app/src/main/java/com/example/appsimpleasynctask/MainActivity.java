package com.example.appsimpleasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TEXT_STATE = "currentText";

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView1);
        button   = findViewById(R.id.button);

        if(savedInstanceState != null){

            String res = savedInstanceState.getString(TEXT_STATE);
            textView.setText(res);

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SimpleAsyncTask(MainActivity.this,textView).execute();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString(TEXT_STATE, textView.getText().toString());

        super.onSaveInstanceState(outState);
    }
}