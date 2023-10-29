package com.ambapharm.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.ambapharm.R;
import com.ambapharm.data.models.ConstantFncNum;
import com.ambapharm.databinding.ActivityAddfncBinding;

public class AddFnc extends AppCompatActivity {
    private ActivityAddfncBinding binding;
    private boolean isItemSelected = false;
    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddfncBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupAutoCompleteTextView();
        setupButtonListener();
    }

    private void setupAutoCompleteTextView() {
        String[] items = getResources().getStringArray(R.array.planets_array);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this, R.layout.item_list_number, items);
        binding.auto.setAdapter(itemAdapter);
        binding.auto.setOnItemClickListener((parent, view, position, id) -> {
            isItemSelected = true;
            selectedItem = itemAdapter.getItem(position);
        });
    }

    private void setupButtonListener() {
        binding.nextIssue.setOnClickListener(v -> {
            if (!isItemSelected) {
                Toast.makeText(this, R.string.select_fnc, Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, AddIssue.class);
                intent.putExtra(ConstantFncNum.EXTRA_KEY, selectedItem);
                startActivity(intent);
            }
        });
    }
}
