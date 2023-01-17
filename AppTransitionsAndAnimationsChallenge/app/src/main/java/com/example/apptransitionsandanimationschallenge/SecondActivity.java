package com.example.apptransitionsandanimationschallenge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Second Activity for the TransitionsAndAnimations app.  Most of the
 * transition and animation behavior is defined in the BaseClass.
 */
public class SecondActivity extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize the views.
        ImageView redBlock = findViewById(R.id.redBlock);
        ImageView blueBlock = findViewById(R.id.blueBlock);
        ImageView yellowBlock = findViewById(R.id.yellowBlock);
        ImageView androidBlock = findViewById(R.id.androidBlock);

        // Set the methods from the base class to the appropriate ImageViews.
        explodeTransition(this, redBlock);
        fadeTransition(this, blueBlock);
        rotateView(yellowBlock);
        switchAnimation(androidBlock,blueBlock,new Intent(this,MainActivity.class),this);
    }
}