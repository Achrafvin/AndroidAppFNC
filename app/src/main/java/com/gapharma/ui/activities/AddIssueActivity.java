package com.gapharma.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import com.gapharma.utils.ConstantFncNum;
import com.gapharma.R;
import com.gapharma.databinding.ActivityIssueBinding;

public class AddIssueActivity extends BaseActivity {

    private ActivityIssueBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeUI();
    }

    private void initializeUI() {
        binding = ActivityIssueBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        updateHeaderWithUserDetails();

        String fncValue = extractIntentExtra(ConstantFncNum.FNC_KEY);
        if (fncValue == null) {
            displayErrorMessageAndFinish();
            return;
        }

        initializeRadioGroup();
        setupNextButton(fncValue);
    }

    private void initializeRadioGroup() {
        binding.radioGroup.clearCheck();
    }

    private void setupNextButton(String fncValue) {
        binding.nextCmt.setOnClickListener(view -> processNextButtonClick(fncValue));
    }

    private void processNextButtonClick(String fncValue) {
        int selectedId = binding.radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            navigateToAddCommentActivity(selectedId, fncValue);
        } else {
            Toast.makeText(this, R.string.promptSelectOption, Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToAddCommentActivity(int selectedId, String fncValue) {
        RadioButton selectedRadioButton = findViewById(selectedId);
        String selectedIssue = selectedRadioButton.getText().toString();

        Intent intent = new Intent(this, AddFncActivity.class);
        intent.putExtra("selectedIssue", selectedIssue);
        intent.putExtra(ConstantFncNum.FNC_KEY, fncValue);
        startActivity(intent);
    }

    private String extractIntentExtra(String key) {
        return getIntent().getStringExtra(key);
    }

    private void displayErrorMessageAndFinish() {
        Toast.makeText(this, R.string.messageErrorOccurred, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void updateHeaderWithUserDetails() {
        updateHeaderWithUserName();
    }
}
