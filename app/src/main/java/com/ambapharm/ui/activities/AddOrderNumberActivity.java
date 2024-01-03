package com.ambapharm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.ambapharm.R;
import com.ambapharm.databinding.ActivityAddordernumberBinding;
import com.ambapharm.helpers.ConstantFncNum;

/**
 * Activity for selecting or entering an order number to create a new FNC (pharmaceutical product anomaly report).
 * Users can choose from a list of existing order numbers or enter a new one. The selected or entered order number
 * is then used to create a new FNC in the subsequent activity.
 */
public class AddOrderNumberActivity extends BaseActivity {
    private ActivityAddordernumberBinding binding;
    private boolean isItemSelected = false;
    private String selectedItem;


    /**
     * Called when the activity is starting. Initializes the activity setup.
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
     * Initializes the activity's UI components and sets up data bindings.
     */
    private void initializeActivity() {
        binding = ActivityAddordernumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configureToolbar();
        setupAutoCompleteTextView();
        setupNextIssueButtonListener();
    }

    /**
     * Configures the toolbar for the activity, including setting up a custom toolbar and updating the header.
     */
    private void configureToolbar() {
        setupToolbar();
        updateHeaderWithUserName();
    }

    /**
     * Initializes the AutoCompleteTextView for selecting an existing order number.
     */
    private void setupAutoCompleteTextView() {
        String[] items = getResources().getStringArray(R.array.planets_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        binding.auto.setAdapter(adapter);
        binding.auto.setOnItemClickListener((parent, view, position, id) -> {
            isItemSelected = true;
            selectedItem = adapter.getItem(position);
        });
        binding.auto.setFreezesText(false);
    }

    /**
     * Sets up the listener for the 'Next Issue' button. This button proceeds to the next step in the FNC creation process.
     */
    private void setupNextIssueButtonListener() {
        binding.nextIssue.setOnClickListener(v -> processNextIssue());
    }


    /**
     * Processes the selection or entry of the next issue (order number) for the FNC.
     * Validates the input and navigates to the next activity for adding comments to the FNC.
     */
    private void processNextIssue() {
        hideKeyboard();
        String inputText = binding.auto.getText().toString();
        if (!isItemSelected && inputText.isEmpty()) {
            Toast.makeText(this, R.string.errorSelectOrder, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isItemSelected) {
            selectedItem = inputText;
        }
        navigateToAddCommentActivity();
    }


    /**
     * Navigates to the AddFncActivity, passing the selected or entered order number.
     */
    private void navigateToAddCommentActivity() {
        Intent intent = new Intent(this, AddFncActivity.class);
        intent.putExtra(ConstantFncNum.FNC_KEY, selectedItem);
        startActivity(intent);
    }
}
