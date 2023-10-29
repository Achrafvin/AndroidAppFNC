package com.ambapharm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ambapharm.R;
import com.ambapharm.data.models.ConstantFncNum;
import com.ambapharm.databinding.ActivityIssueBinding;

public class AddIssue extends AppCompatActivity {

    private ActivityIssueBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIssueBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String value = getIntent().getStringExtra(ConstantFncNum.EXTRA_KEY);
        if (value == null) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        setupRadioGroup();
        setupNextButton(value);
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
                Toast.makeText(AddIssue.this, R.string.select_option, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToAddCmtActivity(String selectedValue, String value) {
        Intent intent = new Intent(AddIssue.this, AddCmt.class);
        intent.putExtra("selectedValue", selectedValue);
        intent.putExtra(ConstantFncNum.EXTRA_KEY, value);
        startActivity(intent);
    }
}
