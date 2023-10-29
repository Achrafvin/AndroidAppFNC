package com.ambapharm.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ambapharm.R;
import com.ambapharm.databinding.ActivityDashboardBinding;

public class Dashboard extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListeners();
    }

    private void setupListeners() {
        binding.addNfc.setOnClickListener(view -> navigateToAddFnc());
        binding.updateNfc.setOnClickListener(view -> navigateToUpdateFnc());
    }

    private void navigateToAddFnc() {
        Intent addIntent = new Intent(Dashboard.this, AddFnc.class);
        startActivity(addIntent);
    }

    private void navigateToUpdateFnc() {
        Intent updateIntent = new Intent(Dashboard.this, UpdateFnc.class);
        startActivity(updateIntent);
    }
}
