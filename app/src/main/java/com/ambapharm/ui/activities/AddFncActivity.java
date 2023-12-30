package com.ambapharm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.ambapharm.R;
import com.ambapharm.helpers.ConstantFncNum;
import com.ambapharm.databinding.ActivityAddfncBinding;

public class AddFncActivity extends BaseActivity {
    private ActivityAddfncBinding binding;
    private boolean isItemSelected = false;
    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddfncBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupToolbar();
        setupAutoCompleteTextView();
        setupButtonListener();
        updateHeaderWithUserName();
    }

    private void setupAutoCompleteTextView() {
        String[] items = getResources().getStringArray(R.array.planets_array);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        binding.auto.setAdapter(itemAdapter);
        binding.auto.setOnItemClickListener((parent, view, position, id) -> {
            isItemSelected = true;
            selectedItem = itemAdapter.getItem(position);
        });
        binding.auto.setFreezesText(false);
    }

    private void setupButtonListener() {
        binding.nextIssue.setOnClickListener(v -> {
            String inputText = binding.auto.getText().toString();
            if (!isItemSelected && inputText.isEmpty()) {
                Toast.makeText(this, R.string.select_fnc, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isItemSelected) {
                selectedItem = inputText;
            }
            Intent intent = new Intent(this, AddCommentActivity.class);
            intent.putExtra(ConstantFncNum.FNC_KEY, selectedItem);
            startActivity(intent);
        });
    }
}
