package com.example.learntomvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.learntomvvm.R;
import com.example.learntomvvm.databinding.ActivityMainBinding;
import com.example.learntomvvm.viewmodels.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new LoginViewModel());
        binding.executePendingBindings();

    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if(message != null) Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

}