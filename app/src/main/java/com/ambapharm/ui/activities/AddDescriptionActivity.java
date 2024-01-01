package com.ambapharm.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ambapharm.R;
import com.ambapharm.databinding.ActivityAddDescriptionBinding;
import com.journeyapps.barcodescanner.ScanIntentResult;

public class AddDescriptionActivity extends BaseActivity {

    private ActivityAddDescriptionBinding binding;
    private boolean isItemSelected = false;
    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeActivity();

    }

    private void initializeActivity() {
        setupCustomeToolbar();
        setupAutoCompleteTextView();
        setupBarcodeScannerButton();
        updateDescription();

    }

    private void setupCustomeToolbar() {
        setSupportActionBar(binding.addImg.topAppBar);
        binding.addImg.topAppBar.setNavigationIcon(null);
        binding.addImg.topAppBar.post(() -> binding.addImg.topAppBar.setTitle(null));
        updateHeaderWithUserName();
    }

    private void setupAutoCompleteTextView() {
        String[] items = getResources().getStringArray(R.array.issue_type);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        binding.typeProblem.setAdapter(itemAdapter);
        binding.typeProblem.setOnItemClickListener((parent, view, position, id) -> {
            isItemSelected = true;
            selectedItem = itemAdapter.getItem(position);
        });
        binding.typeProblem.setFreezesText(false);
    }

    private void setupBarcodeScannerButton() {
        binding.codeIcon.setOnClickListener(v -> scanBarcode());
    }

    private void updateDescription() {
        if (getIntent().hasExtra("EXTRA_TITLE")) {
            binding.codeNum.setText(getIntent().getStringExtra("EXTRA_TITLE"));
            binding.typeProblem.setText(getIntent().getStringExtra("EXTRA_SUBTITLE"));
            binding.quantityTxt.setText(getIntent().getStringExtra("EXTRA_NUM"));
            binding.issueCmt2.setText(getIntent().getStringExtra("EXTRA_COMMENT"));
            binding.addToLigne.setText("Mettre à jour");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        MenuItem item = menu.findItem(R.id.headerIcon);
        item.setIcon(R.drawable.ic_close);
        item.setTitle(R.string.buttonClose);
        return true;
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
            binding.codeNum.setText(result.getContents());
        } else {
            Toast.makeText(this, result.getErrorCorrectionLevel() != null ? "Scan error: " + result.getErrorCorrectionLevel() : getString(R.string.errorLoginFailed), Toast.LENGTH_LONG).show();
        }
    }
}
