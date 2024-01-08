package com.gapharma.ui.activities;

import android.os.Bundle;
import com.gapharma.R;
import com.gapharma.databinding.ActivityAppSettingsBinding;
import com.gapharma.utils.GenericTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AppSettingsActivity extends BaseActivity {

    private ActivityAppSettingsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
    }

    private void initializeActivity() {
        binding = ActivityAppSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configureToolbarAndHeader();
        setupTextWatchers();
        setupRegisterButton();

    }

    private void configureToolbarAndHeader() {
        setupToolbar();
        updateHeaderWithUserName();
    }


    private void setupTextWatchers() {
        binding.inputFncState.addTextChangedListener(new GenericTextWatcher(binding.inputFncStateLayout));
        binding.inputFncType.addTextChangedListener(new GenericTextWatcher(binding.inputFncRepLayout));
        binding.inputFncRep.addTextChangedListener(new GenericTextWatcher(binding.inputFncTypeLayout));

    }


    private void setupRegisterButton() {
        binding.buttonAddAppSettings.setOnClickListener(v->{
            hideKeyboard();
            if (isFormValid()) {
                //TODO: Register app settings
            }
        });
    }


    private boolean isFormValid() {
        return validateField(binding.inputFncState, binding.inputFncStateLayout, R.string.errorEmptyState) &&
                validateField(binding.inputFncType, binding.inputFncTypeLayout, R.string.errorEmptyType) &&
                validateField(binding.inputFncRep, binding.inputFncRepLayout, R.string.errorEmptyDir);
    }

    private boolean validateField(TextInputEditText field, TextInputLayout layout, int errorMessageResId) {
        if (Objects.requireNonNull(field.getText()).toString().isEmpty()) {
            layout.setError(getString(errorMessageResId));
            return false;
        }
        layout.setError(null);
        return true;
    }


}