package com.example.apphellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button toast, count;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        toast.setOnClickListener(showToast);
        count.setOnClickListener(countUp);

    }

    View.OnClickListener showToast = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try{
                alert();
            }catch (Exception e){
                Log.e("MainActivity", e.toString());
            }
        }
    };

    View.OnClickListener countUp = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try{
                result.setText(String.valueOf(Integer.parseInt(result.getText().toString())+1));
            }catch (Exception e){
                Log.e("MainActivity", e.toString());
            }
        }
    };

    public void alert(){
        Toast.makeText(this,R.string.toast_message,Toast.LENGTH_LONG).show();
    }

    public void initComponents(){
        toast = findViewById(R.id.button_toast);
        count = findViewById(R.id.button_count);
        result = findViewById(R.id.textView);
    }

}