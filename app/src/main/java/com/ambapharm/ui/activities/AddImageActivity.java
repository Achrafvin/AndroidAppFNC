package com.ambapharm.ui.activities;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ambapharm.R;
import com.ambapharm.databinding.ActivityAddImageBinding;
import com.google.android.material.appbar.MaterialToolbar;

public class AddImageActivity extends BaseActivity {

    private MaterialToolbar toolbar;

    private ActivityAddImageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);
        binding = ActivityAddImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupToolbar(binding.addImg.topAppBar);
        updateHeaderWithUserName();
    }

    private void setupToolbar(MaterialToolbar toolbar) {
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        configureMenuItem(menu.findItem(R.id.headerIcon), R.drawable.ic_close);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.headerIcon) {
            hideKeyboard();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configureMenuItem(MenuItem item, int iconResId) {
        item.setIcon(iconResId);
        Drawable drawable = DrawableCompat.wrap(item.getIcon());
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.violet));
        item.setIcon(drawable);
    }
}