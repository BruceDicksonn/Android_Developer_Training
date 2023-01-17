package com.example.appmaterialme;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Base  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void trocar(ImageView first, ImageView second, Intent intent, Context context) {

        ActivityOptions options = ActivityOptions
                .makeSceneTransitionAnimation(
                        (Activity) context,
                        Pair.create(first, "First Image"),
                        Pair.create(second, "Second Image")
                );

        context.startActivity(intent, options.toBundle());

    }

}
