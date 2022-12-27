package com.example.pickerdate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showTimePick(View view) {
        TimePickFragment timePickFragment = new TimePickFragment();
        timePickFragment.show(getSupportFragmentManager(),"timePick");
    }

    public void showDataPick(View view) {
        DatePickFragment datePickFragment = new DatePickFragment();
        datePickFragment.show(getSupportFragmentManager(), "datePick");
    }

    public void processTimePickResult(int hour, int minute){

        String h = Integer.toString(hour);
        String m = Integer.toString(minute);
        String r = h + ":" + m;

        Toast.makeText(MainActivity.this, r, Toast.LENGTH_LONG).show();

    }

    public void processDatePickResult(int year, int month, int day){

        String y = Integer.toString(year);
        String m = Integer.toString(month);
        String d = Integer.toString(day);
        String r = d + "/" + m + "/" + y;

        Toast.makeText(MainActivity.this, r, Toast.LENGTH_LONG).show();

    }

}