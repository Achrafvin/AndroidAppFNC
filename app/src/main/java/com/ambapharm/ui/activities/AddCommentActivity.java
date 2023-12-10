package com.ambapharm.ui.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ambapharm.R;
import com.ambapharm.databinding.ActivityCommentBinding;
import com.ambapharm.helpers.ConstantFncNum;
import com.ambapharm.ui.viewModels.AddCommentViewModel;
import com.ambapharm.ui.adapters.GenericAdapter;

import java.util.ArrayList;

public class AddCommentActivity extends BaseActivity implements GenericAdapter.OnItemClickListener {

    private GenericAdapter adapter;
    private ActivityCommentBinding binding;
    private AddCommentViewModel viewModel;

    private String toolbarTitle, valueFromRadioButton;
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
        setupFabMenu();
        setupButtonClickListener();
        toggleFncContentVisibility();
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
        adapter = new GenericAdapter(new ArrayList<>(), this);
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
        valueFromRadioButton = getIntent().getStringExtra("selectedValue");
        binding.cmtTitle.setText(valueFromRadioButton != null ? valueFromRadioButton : "");
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
        return true;
    }

    private void setupButtonClickListener() {
        binding.includedToolbar.fabLr.setOnClickListener(v -> navigateToTargetActivity());
    }

    private void navigateToTargetActivity() {
        startActivity(new Intent(this, AddLigneRActivity.class));
    }

}
