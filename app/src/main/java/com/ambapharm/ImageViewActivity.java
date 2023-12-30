package com.ambapharm;

import android.net.Uri;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        ImageView imageView = findViewById(R.id.imageView);
        String imagePath = getIntent().getStringExtra("imagePath");
        if (imagePath != null) {
            imageView.setImageURI(Uri.parse(imagePath));
        }
    }
}