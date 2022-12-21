
package com.example.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.droidcafe.databinding.ActivityOrderBinding;

public class OrderActivity extends AppCompatActivity {

    private ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(getIntent() != null) {

            Bundle extras = getIntent().getExtras();
            String message = extras.getString(MainActivity.EXTRA_MESSAGE);

            TextView textView = binding.orderTextview;
            textView.setText(message);

        }

    }

    public void alert(String msg){
        Toast.makeText(OrderActivity.this,msg,Toast.LENGTH_LONG).show();
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton)view).isChecked();

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
}