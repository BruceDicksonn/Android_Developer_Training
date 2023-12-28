package com.example.projetoestudolocalizacao;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projetoestudolocalizacao.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Intent serviceLocalizacao = new Intent(this, ServiceLocalizacao.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ServiceLocalizacao.checkLocationPermission(this)) {
            Intent intent = new Intent(this, ServiceLocalizacao.class);
            startService(intent);
        }

    }

    @Override
    protected void onDestroy() {

        if(serviceLocalizacao != null) {
            stopService(serviceLocalizacao);
        }

        super.onDestroy();
    }
}