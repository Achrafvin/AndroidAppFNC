package com.ambapharm.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.ambapharm.databinding.ActivityDashboardBinding;

public class DashboardActivity extends BaseActivity {

    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
    }

    private void initializeActivity() {
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configureToolbarAndHeader();
        configureNavigationButtons();
    }

    private void configureToolbarAndHeader() {
        setupToolbar();
        updateHeaderWithUserName();
    }

    private void configureNavigationButtons() {
        binding.addNfc.setOnClickListener(view -> navigateToAddFncActivity());
        binding.updateNfc.setOnClickListener(view -> navigateToFncListActivity());
    }

    private void navigateToAddFncActivity() {
        startActivity(new Intent(this, AddOrderNumberActivity.class));
    }

    private void navigateToFncListActivity() {
        startActivity(new Intent(this, UpdateFncActivity.class));
    }
}
