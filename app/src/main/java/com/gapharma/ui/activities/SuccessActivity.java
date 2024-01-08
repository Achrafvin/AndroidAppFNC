package com.gapharma.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;

import android.os.Looper;
import com.gapharma.R;

/**
 * SuccessActivity is displayed as a confirmation screen after certain actions are successfully completed,
 * such as saving data in the AddFncActivity. After a brief display, it automatically logs out the user.
 */
public class SuccessActivity extends BaseActivity {


    /**
     * Called when the activity is starting. This method sets up a delay after which it logs out the user.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                logoutUser();
            }
        }, 3000);
    }


    /**
     * Logs out the user by clearing the login status and navigating to the login screen.
     */
    private void logoutUser() {
        clearLoginStatus();
        navigateToLogin();
    }


    /**
     * Clears the logged-in user's information from SharedPreferences.
     */
    private void clearLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit().clear();
        editor.apply();
    }


    /**
     * Navigates to the AuthenticationActivity and clears the current activity stack.
     */
    private void navigateToLogin() {
        Intent intent = new Intent(this, AuthenticationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
