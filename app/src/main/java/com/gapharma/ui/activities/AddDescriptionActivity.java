package com.gapharma.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.gapharma.ui.adapters.items.DescriptionItem;
import com.gapharma.ui.viewModels.AddFncViewModel;
import com.gapharma.R;
import com.gapharma.databinding.ActivityAddDescriptionBinding;
import com.journeyapps.barcodescanner.ScanIntentResult;


/**
 * Activity for adding a description to a pharmaceutical product's anomaly report (FNC).
 * This class handles user inputs to create a new report item, including barcode scanning,
 * issue type selection, and text inputs for detailed description.
 */
public class AddDescriptionActivity extends BaseActivity {

    private ActivityAddDescriptionBinding binding;
    private boolean isItemSelected = false;
    private String selectedItem;
    private AddFncViewModel viewModel;


    /**
     * Initializes the activity. Sets up the UI and bindings.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeActivity();

    }

    /**
     * Initializes the components and functionalities of the activity.
     */
    private void initializeActivity() {
        viewModel = AddFncViewModel.getInstance();
        setupCustomeToolbar();
        updateDescription();
        setupAutoCompleteTextView();
        setupBarcodeScannerButton();
        onAddFnc();
    }

    /**
     * Sets up the custom toolbar for the activity.
     */
    private void setupCustomeToolbar() {
        setSupportActionBar(binding.addImg.topAppBar);
        binding.addImg.topAppBar.setNavigationIcon(null);
        binding.addImg.topAppBar.post(() -> binding.addImg.topAppBar.setTitle(null));
        updateHeaderWithUserName();
    }

    /**
     * Sets up the AutoCompleteTextView for issue type selection.
     */
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

    /**
     * Sets up the barcode scanner button for input.
     */
    private void setupBarcodeScannerButton() {
        binding.codeIcon.setOnClickListener(v -> scanBarcode());
    }


    /**
     * Updates the description fields based on the passed intent extras.
     */
    private void updateDescription() {
        if (getIntent().hasExtra("EXTRA_TITLE")) {
            binding.codeNum.setText(getIntent().getStringExtra("EXTRA_TITLE"));
            binding.typeProblem.setText(getIntent().getStringExtra("EXTRA_SUBTITLE"));
            binding.quantityTxt.setText(getIntent().getStringExtra("EXTRA_NUM"));
            binding.issueCmt2.setText(getIntent().getStringExtra("EXTRA_COMMENT"));
            binding.addToLigne.setText("Mettre à jour");
        }
    }


    /**
     * Handles the addition of a new FNC (pharmaceutical product anomaly report).
     * Validates input before adding.
     */
    private void onAddFnc() {
        binding.addToLigne.setOnClickListener(v -> {
            if (validateInput()) {
                String mainTitle = binding.codeNum.getText().toString().trim();
                String subtitle = selectedItem;
                String comment = binding.issueCmt2.getText().toString().trim();
                String num = binding.quantityTxt.getText().toString().trim();
                Long id = getIntent().getLongExtra("EXTRA_ID",0L);
                DescriptionItem newItem = new DescriptionItem(id,mainTitle, subtitle, comment, num);
                viewModel.addDescriptionItem(newItem);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    /**
     * Validates the input fields before creating a new FNC item.
     *
     * @return true if all inputs are valid, false otherwise.
     */
    private boolean validateInput() {
        if (binding.codeNum.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, R.string.errorEmptyCodeProduct, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isItemSelected && binding.typeProblem.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, R.string.errorEmptyProblemType, Toast.LENGTH_SHORT).show();
            return false;
        }else{
            selectedItem = binding.typeProblem.getText().toString().trim();
        }
        if (binding.quantityTxt.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, R.string.errorEmptyQuantity, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.issueCmt2.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, R.string.errorEmptyComment, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    /**
     * Inflates the menu for the activity.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        MenuItem item = menu.findItem(R.id.headerIcon);
        item.setIcon(R.drawable.ic_close);
        item.setTitle(R.string.buttonClose);
        return true;
    }


    /**
     * Handles menu item selection.
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to proceed,
     *                 true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.headerIcon) {
            hideKeyboard();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Called when a barcode is scanned.
     *
     * @param result The result of the barcode scan.
     */
    @Override
    protected void onBarcodeScanned(ScanIntentResult result) {
        if (result.getContents() != null) {
            binding.codeNum.setText(result.getContents());
        } else {
            Toast.makeText(this, result.getErrorCorrectionLevel() != null ? "Scan error: " + result.getErrorCorrectionLevel() : getString(R.string.messageErrorOccurred), Toast.LENGTH_LONG).show();
        }
    }
}
