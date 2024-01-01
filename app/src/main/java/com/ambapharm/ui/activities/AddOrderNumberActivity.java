package com.ambapharm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.ambapharm.R;
import com.ambapharm.databinding.ActivityAddordernumberBinding;
import com.ambapharm.helpers.ConstantFncNum;

public class AddOrderNumberActivity extends BaseActivity {
    private ActivityAddordernumberBinding binding;
    private boolean isItemSelected = false;
    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
    }

    private void initializeActivity() {
        binding = ActivityAddordernumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configureToolbar();
        initializeAutoComplete();
        setupNextIssueButtonListener();
    }

    private void configureToolbar() {
        setupToolbar();
        updateHeaderWithUserName();
    }

    private void initializeAutoComplete() {
        String[] items = getResources().getStringArray(R.array.planets_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        binding.auto.setAdapter(adapter);
        binding.auto.setOnItemClickListener((parent, view, position, id) -> {
            isItemSelected = true;
            selectedItem = adapter.getItem(position);
        });
        binding.auto.setFreezesText(false);
    }

    private void setupNextIssueButtonListener() {
        binding.nextIssue.setOnClickListener(v -> processNextIssue());
    }

    private void processNextIssue() {
        String inputText = binding.auto.getText().toString();
        if (!isItemSelected && inputText.isEmpty()) {
            Toast.makeText(this, R.string.promptSelectOrder, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isItemSelected) {
            selectedItem = inputText;
        }
        navigateToAddCommentActivity();
    }

    private void navigateToAddCommentActivity() {
        Intent intent = new Intent(this, AddFncActivity.class);
        intent.putExtra(ConstantFncNum.FNC_KEY, selectedItem);
        startActivity(intent);
    }
}
