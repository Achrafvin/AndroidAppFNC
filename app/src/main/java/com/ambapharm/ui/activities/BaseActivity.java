package com.ambapharm.ui.activities;

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

import com.ambapharm.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.journeyapps.barcodescanner.ScanIntentResult;

public class BaseActivity extends AppCompatActivity {

    protected ActivityResultLauncher<ScanOptions> barcodeLauncher;
    private MaterialToolbar header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBarcodeScanner();
    }

    private void setupBarcodeScanner() {
        barcodeLauncher = registerForActivityResult(new ScanContract(), this::onBarcodeScanned);
    }

    protected void scanBarcode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Scan a CIP code");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CipCaptureActivity.class);
        options.setBarcodeImageEnabled(true);
        barcodeLauncher.launch(options);
    }

    protected void onBarcodeScanned(ScanIntentResult result) {
    }

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

    protected void setupToolbar() {
        header = findViewById(R.id.topAppBar);
        setSupportActionBar(header);
    }

    private void logoutUser() {
        clearLoginStatus();
        navigateToLogin();
    }

    private void clearLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit().clear();
        editor.apply();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, AuthenticationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    protected void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void updateHeaderWithUserName() {
        header = findViewById(R.id.topAppBar);
        header.setTitle(getUserName());
    }

    private String getUserName() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        return sharedPreferences.getString("userName", "Default Name");
    }
}
