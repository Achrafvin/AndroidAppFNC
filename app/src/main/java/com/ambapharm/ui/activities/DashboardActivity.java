package com.ambapharm.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.ambapharm.databinding.ActivityDashboardBinding;

public class DashboardActivity extends BaseActivity {

    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupToolbar();
        setupNavigation();

        //////////////////////////
        updateHeaderWithUserName();
    }


    private void setupNavigation() {
        binding.addNfc.setOnClickListener(view -> navigateToAddFnc());
        binding.updateNfc.setOnClickListener(view -> navigateToUpdateFnc());
    }

    private void navigateToAddFnc() {
        startActivity(new Intent(this, AddFncActivity.class));
        finish();
    }

    private void navigateToUpdateFnc() {
        startActivity(new Intent(this, FncListActivity.class));
        finish();
    }
}
