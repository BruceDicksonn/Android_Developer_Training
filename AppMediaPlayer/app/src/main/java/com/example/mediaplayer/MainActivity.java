package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.TimedText;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import com.example.mediaplayer.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ActivityMainBinding binding;

    // é um objeto que nos permite executar códigos em uma thread separada da main
    // eles recebem um Runnable como argumento
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mediaPlayer = MediaPlayer.create(this, R.raw.bach);
        mediaPlayer.setOnCompletionListener(onCompletionListener);

        binding.buttonPlay.setOnClickListener(executarSom);
        binding.buttonPause.setOnClickListener(pausarSom);
        binding.buttonStop.setOnClickListener(pararSom);

    }

    // Evento disparado quando o audio terminar
    // Devemos limpar o handler ao final para que nao fique executando as callbacks sem necessidade
    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            handler.removeCallbacks(updateSeekbarRunnable);
            Toast.makeText(getApplicationContext(), "Aúdio finalizado", Toast.LENGTH_SHORT).show();
        }
    };


    // O Runnable é uma interface no Android que representa uma unidade de trabalho que pode ser executada.
    // Basicamente, é um bloco de código que pode ser executado em um momento posterior
    Runnable updateSeekbarRunnable = new Runnable() {
        @Override
        public void run() {

            if(mediaPlayer != null && mediaPlayer.isPlaying()) {
                int currentPos = mediaPlayer.getCurrentPosition();
                binding.seekBar.setProgress(currentPos);
            }
            handler.postDelayed(this, 100);
        }
    };

    View.OnClickListener executarSom = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mediaPlayer != null) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    binding.seekBar.setMin(0);
                    binding.seekBar.setMax(mediaPlayer.getDuration());
                    binding.seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }

                if(!mediaPlayer.isPlaying()){
                    handler.post(updateSeekbarRunnable);
                    mediaPlayer.start();
                }

            }
        }
    };

    View.OnClickListener pausarSom = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                handler.removeCallbacks(updateSeekbarRunnable);
            }

        }
    };

    View.OnClickListener pararSom = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mediaPlayer != null && mediaPlayer.isPlaying()) {

                try {

                    mediaPlayer.stop();
                    mediaPlayer.prepare();
                    mediaPlayer.seekTo(0); // para redefinir a mediaPlayer.currentPosition do para zero

                } catch (IOException e) {
                    e.printStackTrace();
                }

                handler.removeCallbacks(updateSeekbarRunnable);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    binding.seekBar.setProgress(0);
                }
            }
        }
    };

}