package com.gapharma.ui.activities;

import android.os.Bundle;
import com.gapharma.R;
import com.gapharma.databinding.ActivityAddUserBinding;
import com.gapharma.utils.GenericTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AddUserActivity extends BaseActivity {

    private ActivityAddUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeActivity();

    }


    private void initializeActivity() {
        binding = ActivityAddUserBinding.inflate(getLayoutInflater());
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
        binding.inputNom.addTextChangedListener(new GenericTextWatcher(binding.inputNomLayout));
        binding.inputIdentity.addTextChangedListener(new GenericTextWatcher(binding.inputIdentityLayout));
        binding.inputPassword.addTextChangedListener(new GenericTextWatcher(binding.inputPasswordLayout));
        binding.inputRole.addTextChangedListener(new GenericTextWatcher(binding.inputRoleLayout));
        binding.inputEmail.addTextChangedListener(new GenericTextWatcher(binding.inputEmailLayout));
        binding.inputCIP.addTextChangedListener(new GenericTextWatcher(binding.inputCIPLayout));
    }

    private void setupRegisterButton() {
       binding.buttonRegister.setOnClickListener(v->{
           hideKeyboard();
           if (isFormValid()) {
               //TODO: Register user
           }
       });
    }


    private boolean isFormValid() {
        return validateField(binding.inputNom, binding.inputNomLayout, R.string.errorEmptyName) &&
                validateField(binding.inputIdentity, binding.inputIdentityLayout, R.string.errorEmptyIdentity) &&
                validateField(binding.inputPassword, binding.inputPasswordLayout, R.string.errorEmptyPassword) &&
                validateField(binding.inputRole, binding.inputRoleLayout, R.string.errorEmptyRole) &&
                validateEmail(binding.inputEmail, binding.inputEmailLayout) &&
                validateField(binding.inputCIP, binding.inputCIPLayout, R.string.errorEmptyCIP);
    }

    private boolean validateField(TextInputEditText field, TextInputLayout layout, int errorMessageResId) {
        if (Objects.requireNonNull(field.getText()).toString().isEmpty()) {
            layout.setError(getString(errorMessageResId));
            return false;
        }
        layout.setError(null);
        return true;
    }

    private boolean validateEmail(TextInputEditText field, TextInputLayout layout) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (Objects.requireNonNull(field.getText()).toString().isEmpty()) {
            layout.setError(getString(R.string.errorEmptyEmail));
            return false;
        } else if (!field.getText().toString().matches(emailPattern)) {
            layout.setError(getString(R.string.errorInvalidEmail));
            return false;
        }
        layout.setError(null);
        return true;
    }

}