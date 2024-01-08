package com.gapharma.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import com.gapharma.databinding.ActivityDashboardBinding;


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
        String userRole = getUserRole();

        if ("ROLE_ADMIN".equals(userRole)) {
            binding.buttonAddUser.setVisibility(View.VISIBLE);
            binding.buttonSettings.setVisibility(View.VISIBLE);
            binding.buttonAddUser.setOnClickListener(view -> navigateToAddUserActivity());
            binding.buttonSettings.setOnClickListener(view -> navigateToAppSettingsActivity());
        } else {
            binding.buttonAddUser.setVisibility(View.GONE);
            binding.buttonSettings.setVisibility(View.GONE);
        }
    }


    private String getUserRole() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        return sharedPreferences.getString("userRole", "null");
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


    /**
     * Navigates to the AddUserActivity to allow the admin to add a new user.
     */
    private void navigateToAddUserActivity() {
        startActivity(new Intent(this, AddUserActivity.class));
    }


    /**
     * Navigates to the AppSettingsActivity to allow the admin to change the app settings.
     */
    private void navigateToAppSettingsActivity() {
        startActivity(new Intent(this, AppSettingsActivity.class));
    }

}
