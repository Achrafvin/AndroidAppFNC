package com.ambapharm.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ambapharm.R;
import com.ambapharm.databinding.ActivityAddLrBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.journeyapps.barcodescanner.ScanIntentResult;

public class AddLigneRActivity extends BaseActivity {

    private MaterialToolbar toolbar;
    private ActivityAddLrBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lr);
        binding = ActivityAddLrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupToolbar(binding.addImg.topAppBar);
        updateHeaderWithUserName();
        binding.codeIcon.setOnClickListener(v->{
            scanBarcode();
        });
    }

    private void setupToolbar(MaterialToolbar toolbar) {
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(null);
        toolbar.post(() -> toolbar.setTitle(null));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        configureMenuItem(menu.findItem(R.id.headerIcon), R.drawable.ic_close);
        return true;
    }
    private void configureMenuItem(MenuItem item, int iconResId) {
        item.setIcon(iconResId);
        item.setTitle(R.string.close);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.headerIcon) {
            hideKeyboard();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onBarcodeScanned(ScanIntentResult result) {
        if (result.getContents() != null) {
            String barcodeData = result.getContents();
            binding.codeNum.setText(barcodeData);
        }else {
            if (result.getErrorCorrectionLevel() != null) {
                Toast.makeText(this, "Scan error: " + result.getErrorCorrectionLevel(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Aucun code-barres capturé, le scan a échoué.", Toast.LENGTH_LONG).show();
            }
        }
    }


}