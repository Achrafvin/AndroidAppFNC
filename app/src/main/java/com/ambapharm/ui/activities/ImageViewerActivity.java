package com.ambapharm.ui.activities;

import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ambapharm.R;
import com.bumptech.glide.Glide;

public class ImageViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        ImageView imageView = findViewById(R.id.imageView);
        String imagePath = getIntent().getStringExtra("imagePath");
        if (imagePath != null) {
            Glide.with(this).load(imagePath).fitCenter().into(imageView);
        }
    }
}