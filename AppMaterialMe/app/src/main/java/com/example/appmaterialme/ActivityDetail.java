package com.example.appmaterialme;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appmaterialme.databinding.ActivityDetailBinding;

public class ActivityDetail extends AppCompatActivity {

    /**
     * Initializes the activity, filling in the data from the Intent.
     *
     * @param savedInstanceState Contains information about the saved state
     *                           of the activity.
     */

    static ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the views.
        TextView sportsTitle = findViewById(R.id.titleDetail);
        ImageView sportsImage = findViewById(R.id.sportsImageDetail);

        // Set the text from the Intent extra.
        sportsTitle.setText(getIntent().getStringExtra("title"));

        // Load the image using the Glide library and the Intent extra.
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(sportsImage);
    }
}
