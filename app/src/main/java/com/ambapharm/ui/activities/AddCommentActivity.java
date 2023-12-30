package com.ambapharm.ui.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.PopupMenu;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ambapharm.R;
import com.ambapharm.databinding.ActivityCommentBinding;
import com.ambapharm.helpers.ConstantFncNum;
import com.ambapharm.ui.adapters.ListItem;
import com.ambapharm.ui.viewModels.AddCommentViewModel;
import com.ambapharm.ui.adapters.GenericAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddCommentActivity extends BaseActivity implements GenericAdapter.OnItemClickListener,GenericAdapter.OnItemDeleteListener{

    private GenericAdapter adapter;
    private ActivityCommentBinding binding;
    private AddCommentViewModel viewModel;

    private String toolbarTitle;
    private static final float ROTATION_START = 0f;
    private static final float ROTATION_END = 45f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(AddCommentViewModel.class);
        initializeViews();


    }

    private void initializeViews() {

        retrieveIntentData();
        configureToolbar();
        setupRecyclerView();
        viewModel.getItems().observe(this, items -> {
            adapter.setItems(items);

        });
        toggleFncContentVisibility();
        setupFabMenu();
        setupButtonClickListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (binding.includedToolbar.fabPj.getVisibility() == View.VISIBLE) {
            toggleFabMenu(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        configureMenuItem(menu.findItem(R.id.headerIcon), R.drawable.ic_save);
        return true;
    }

    private void configureMenuItem(MenuItem item, int iconResId) {
        item.setIcon(iconResId);
        item.setTitle(R.string.save);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.headerIcon) {
            hideKeyboard();
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        adapter = new GenericAdapter(this,new ArrayList<>(), this,this);
        binding.cardView.setLayoutManager(new LinearLayoutManager(this));
        binding.cardView.setAdapter(adapter);
        viewModel.getItems().observe(this, adapter::setItems);
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click
    }

    private void retrieveIntentData() {
        toolbarTitle = getIntent().getStringExtra(ConstantFncNum.FNC_KEY);
    }

    private void configureToolbar() {
        setSupportActionBar(binding.includedToolbar.appBarLayout.topAppBar);
        binding.includedToolbar.appBarLayout.topAppBar.setTitle(
                toolbarTitle != null && !toolbarTitle.isEmpty() ? toolbarTitle : getString(R.string.numFnc)
        );
        binding.includedToolbar.appBarLayout.topAppBar.setNavigationIcon(null);
    }

    private void setupFabMenu() {
        binding.includedToolbar.fabMenu.setOnClickListener(view ->{
            hideKeyboard();
            toggleFabMenu(binding.includedToolbar.fabPj.getVisibility() == View.GONE);
        } );
    }

    private void toggleFabMenu(boolean show) {
        int visibility = show ? View.VISIBLE : View.GONE;
        binding.includedToolbar.fabPj.setVisibility(visibility);
        binding.includedToolbar.fabCam.setVisibility(visibility);
        binding.includedToolbar.fabLr.setVisibility(visibility);
        binding.overlay.setVisibility(visibility);
        binding.includedToolbar.pjText.setVisibility(visibility);
        binding.includedToolbar.caText.setVisibility(visibility);
        binding.includedToolbar.lrText.setVisibility(visibility);

        startFabAnimation(show);
    }

    private void startFabAnimation(boolean open) {
        float startRotation = open ? ROTATION_START : ROTATION_END;
        float endRotation = open ? ROTATION_END : ROTATION_START;
        ObjectAnimator rotate = ObjectAnimator.ofFloat(binding.includedToolbar.fabMenu, "rotation", startRotation, endRotation);
        rotate.start();
    }

    private void toggleFncContentVisibility() {
        if (fncDataAvailable()) {
            binding.fncCardEmpty.setVisibility(View.GONE);
            binding.fncContentContainer.setVisibility(View.VISIBLE);
        } else {
            binding.fncCardEmpty.setVisibility(View.VISIBLE);
            binding.fncContentContainer.setVisibility(View.GONE);
        }
    }

    private boolean fncDataAvailable() {
        List<ListItem> items = viewModel.getItems().getValue();
        return items != null && !items.isEmpty();
    }



    private void setupButtonClickListener() {
        binding.includedToolbar.fabLr.setOnClickListener(v -> navigateToTargetActivity());
        binding.includedToolbar.fabCam.setOnClickListener(v -> openPopUp());
    }

    private void navigateToTargetActivity() {
        startActivity(new Intent(this, AddLigneRActivity.class));
    }

    private void openPopUp() {
        PopupMenu popup = new PopupMenu(this, findViewById(R.id.fab_cam));
        popup.getMenuInflater().inflate(R.menu.addimage, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_select_gallery:
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 1);
                    break;
                case R.id.menu_take_photo:
                    // Code to take a photo
                    break;
            }
            return true;
        });
        popup.show();
    }

    @Override
    public void onDeleteItem(int position) {
        viewModel.removeItem(position);
    }



}
