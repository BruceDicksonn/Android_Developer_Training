package com.example.executandovideos;

import android.app.StatusBarManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;

import com.example.executandovideos.databinding.ActivityPlayerBinding;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class PlayerActivity extends AppCompatActivity {

    ActivityPlayerBinding binding;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        // esconder a statusBar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);


        String path = "android.resource://" + getPackageName() + "/" + R.raw.video;
        
        // define o controlador padrão do android
        binding.videoView.setMediaController(new MediaController(this));
        binding.videoView.setVideoPath(path);

        binding.videoView.start();



    }
}