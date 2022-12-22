package com.example.implicitintents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText uri, location, share;
    private ImageView image;
    private Button btnUri, btnLocation, btnShare, btnPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    View.OnClickListener openWebsite = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String txt = uri.getText().toString();
            Uri url = Uri.parse(txt);
            Intent intent = new Intent(Intent.ACTION_VIEW, url);

            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            } else {
                Log.e("MainActivity", "Não é possível lidar com essa intent");
            }
        }
    };

    View.OnClickListener openLocation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String loc = location.getText().toString();
            Uri uri = Uri.parse("geo:0,0?q=" + loc);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            } else {
                Log.e("MainActivity", "Não é possível lidar com essa intent");
            }

        }
    };

    View.OnClickListener shareText = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String txt = share.getText().toString();
            String mimeType = "text/plain";

            ShareCompat.IntentBuilder
                    .from(MainActivity.this)
                    .setType(mimeType)
                    .setChooserTitle("Compartilhar texto com:")
                    .setText(txt)
                    .startChooser();

        }
    };

    View.OnClickListener openCamera = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(intent, 200);

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 200){

            Bitmap bitmap = null;

            if(resultCode == RESULT_OK){

                    Bundle extras = data.getExtras();
                    bitmap = (Bitmap) extras.get("data");

            }

            if(bitmap != null) {
                image.setImageBitmap(bitmap);
                return;
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void initComponents(){

        uri = findViewById(R.id.website_edittext);
        location = findViewById(R.id.location_edittext);
        share = findViewById(R.id.share_edittext);
        image = findViewById(R.id.imagem);

        btnUri = findViewById(R.id.button_uri);
        btnUri.setOnClickListener(openWebsite);

        btnLocation = findViewById(R.id.button_location);
        btnLocation.setOnClickListener(openLocation);

        btnShare = findViewById(R.id.button_share);
        btnShare.setOnClickListener(shareText);

        btnPhoto = findViewById(R.id.button_photo);
        btnPhoto.setOnClickListener(openCamera);
    }
}