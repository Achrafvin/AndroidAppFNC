package com.gapharma.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.gapharma.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.journeyapps.barcodescanner.ScanIntentResult;


/**
 * A base activity class that provides common functionalities to all child activities.
 * This includes handling barcode scanning, user authentication management, keyboard hiding,
 * and toolbar setup.
 */
public class BaseActivity extends AppCompatActivity {

    protected ActivityResultLauncher<ScanOptions> barcodeLauncher;
    private MaterialToolbar header;


    /**
     * Called when the activity is starting. This method sets up the barcode scanner.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBarcodeScanner();
    }

    /**
     * Sets up the barcode scanner with the necessary options and launch configurations.
     */
    private void setupBarcodeScanner() {
        barcodeLauncher = registerForActivityResult(new ScanContract(), this::onBarcodeScanned);
    }


    /**
     * Initiates the barcode scanning process with specific scan options.
     */
    protected void scanBarcode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Scan a CIP code");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CipCaptureActivity.class);
        options.setBarcodeImageEnabled(true);
        barcodeLauncher.launch(options);
    }


    /**
     * Handles the result of a barcode scan. This method is intended to be overridden by child classes.
     *
     * @param result The result of the barcode scan.
     */
    protected void onBarcodeScanned(ScanIntentResult result) {}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.headerIcon) {
            logoutUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Sets up the toolbar for the activity. This method finds the toolbar view and sets it as the support action bar.
     */
    protected void setupToolbar() {
        header = findViewById(R.id.topAppBar);
        setSupportActionBar(header);
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


    /**
     * Hides the soft keyboard if it is visible.
     */
    protected void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    /**
     * Updates the header (toolbar) title with the logged-in user's name.
     */
    protected void updateHeaderWithUserName() {
        header = findViewById(R.id.topAppBar);
        header.setTitle(getUserName());
    }


    /**
     * Retrieves the logged-in user's name from SharedPreferences.
     *
     * @return The user's name or a default string if not available.
     */
    private String getUserName() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        return sharedPreferences.getString("userName", "Nom d'utilisateur");
    }
}
