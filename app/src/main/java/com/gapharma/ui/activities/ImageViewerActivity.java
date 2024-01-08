package com.gapharma.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.gapharma.databinding.ActivityImageViewerBinding;
import com.bumptech.glide.Glide;


/**
 * Activity for viewing images.
 * It retrieves the path of an image file passed through the intent and displays the image using Glide library.
 */
public class ImageViewerActivity extends AppCompatActivity {

    private ActivityImageViewerBinding binding;

    /**
     * Called when the activity is starting. This method sets up the view for displaying the image.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intializeView();

    }

    /**
     * Initializes the view by setting the image in the viewer and configuring the close button.
     */
    private void intializeView() {
        setTheImageInViewer();
        closeTheViewer();
    }


    /**
     * Sets the image in the image viewer using the Glide library.
     */
    private void setTheImageInViewer() {
        String imagePath = getIntent().getStringExtra("imagePath");
        if (imagePath != null) {
            Glide.with(this).load(imagePath).fitCenter().into(binding.imageView);
        }
    }

    /**
     * Sets up a listener for the close button to finish the activity.
     */
    private void closeTheViewer() {
        binding.closeViewer.setOnClickListener(v -> {
            finish();
        });
    }
}