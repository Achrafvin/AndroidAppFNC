package com.ambapharm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ambapharm.R;
import com.ambapharm.helpers.ConstantFncNum;
import com.ambapharm.databinding.ActivityIssueBinding;

public class AddIssueActivity extends BaseActivity {

    private ActivityIssueBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIssueBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupToolbar();
        String value = getIntent().getStringExtra(ConstantFncNum.FNC_KEY);
        if (value == null) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        setupRadioGroup();
        setupNextButton(value);
        updateHeaderWithUserName();
    }

    private void setupRadioGroup() {
        binding.radioGroup.clearCheck();
    }

    private void setupNextButton(String value) {
        binding.nextCmt.setOnClickListener(view -> {
            int selectedId = binding.radioGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRadioButton = findViewById(selectedId);
                String selectedValue = selectedRadioButton.getText().toString();
                navigateToAddCmtActivity(selectedValue, value);
            } else {
                Toast.makeText(AddIssueActivity.this, R.string.select_option, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToAddCmtActivity(String selectedValue, String value) {
        Intent intent = new Intent(AddIssueActivity.this, AddCommentActivity.class);
        intent.putExtra("selectedValue", selectedValue);
        intent.putExtra(ConstantFncNum.FNC_KEY, value);
        startActivity(intent);
    }
}
