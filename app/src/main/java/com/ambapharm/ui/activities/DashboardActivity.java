package com.ambapharm.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.ambapharm.databinding.ActivityDashboardBinding;


/**
 * DashboardActivity serves as the main dashboard for the application,
 * providing navigation options to either create a new FNC (pharmaceutical product anomaly report)
 * or update an existing one. This activity extends BaseActivity, inheriting common functionalities.
 */
public class DashboardActivity extends BaseActivity {

    private ActivityDashboardBinding binding;


    /**
     * Called when the activity is starting. This method initializes the activity setup.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
    }


    /**
     * Initializes the activity by inflating its layout, setting up the toolbar, and configuring navigation buttons.
     */
    private void initializeActivity() {
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configureToolbarAndHeader();
        configureNavigationButtons();
    }


    /**
     * Configures the toolbar and updates the header with the user's name.
     */
    private void configureToolbarAndHeader() {
        setupToolbar();
        updateHeaderWithUserName();
    }


    /**
     * Sets up navigation buttons that allow the user to add or update an FNC.
     */
    private void configureNavigationButtons() {
        binding.addNfc.setOnClickListener(view -> navigateToAddFncActivity());
        binding.updateNfc.setOnClickListener(view -> navigateToFncListActivity());
    }


    /**
     * Navigates to the AddOrderNumberActivity to allow the user to add a new FNC.
     */
    private void navigateToAddFncActivity() {
        startActivity(new Intent(this, AddOrderNumberActivity.class));
    }

    /**
     * Navigates to the UpdateFncActivity to allow the user to update an existing FNC.
     */
    private void navigateToFncListActivity() {
        startActivity(new Intent(this, UpdateFncActivity.class));
    }
}
