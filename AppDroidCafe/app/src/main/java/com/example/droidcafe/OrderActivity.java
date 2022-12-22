
package com.example.droidcafe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.droidcafe.databinding.ActivityOrderBinding;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initComponents();
    }

    public void alert(String msg){
        Toast.makeText(OrderActivity.this,msg,Toast.LENGTH_LONG).show();
    }

    public void onRadioButtonClicked(View view) {
        switch (view.getId()){
            case R.id.method_sameDay:
                    alert(getString(R.string.same_day_messenger_service));
            break;
            case R.id.method_nextDay:
                   alert(getString(R.string.next_day_ground_delivery));
            break;
            case R.id.method_pickup:
                   alert(getString(R.string.pick_up));
             break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String label = adapterView.getItemAtPosition(i).toString();
        if(!label.equals("None")) alert(label);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void showMessageCheckBoxes(ArrayList<CheckBox> checkBoxes){
        String msg = "";

        for(int i = 0; i < checkBoxes.size(); i++){

            msg += checkBoxes.get(i).getText().toString() + " ";

        }

        alert(msg);

    }

    public void initComponents(){

        if(getIntent() != null) {

            Bundle extras = getIntent().getExtras();
            String message = extras.getString(MainActivity.EXTRA_MESSAGE);

            TextView textView = binding.orderTextview;
            textView.setText(message);

        }

        binding.labelSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                                                                                    R.array.spinner_options,
                                                                                    android.R.layout.simple_spinner_dropdown_item);
        if(binding.labelSpinner != null) {
            binding.labelSpinner.setAdapter(adapter);
            binding.labelSpinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }

    }
}