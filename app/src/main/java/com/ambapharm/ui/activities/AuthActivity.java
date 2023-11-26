package com.ambapharm.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;

import com.ambapharm.data.entities.User;
import com.ambapharm.ui.viewModels.AuthViewModel;
import com.ambapharm.databinding.ActivityAuthBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.journeyapps.barcodescanner.ScanIntentResult;



public class AuthActivity extends BaseActivity {

    private ActivityAuthBinding binding;
    private AuthViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isUserLoggedIn()) {
            navigateToDashboard();
            return;
        }
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        setupLoginButton();
        setupObservers();
        setupTextWatchers();

        //CAN BE REMOVED
        binding.imageView.setOnClickListener(v->{
            scanBarcode();
        });

        viewModel.getLoggedInUser().observe(this, user -> {
            if (user != null) {
                saveUserInfo(user);
                navigateToDashboard();
            }
        });

        viewModel.getLoginErrorMessage().observe(this, errorMsg -> {
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
        });

    }

        ////////////////////
        private void saveUserInfo(User user) {
    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("userName", user.getName());
    editor.apply();
}

    //CAN BE REMOVED
    @Override
    protected void onBarcodeScanned(ScanIntentResult result) {
        if (result.getContents() != null) {
            String barcodeData = result.getContents();
            viewModel.loginWithBarcode(barcodeData);
        }else {
            if (result.getErrorCorrectionLevel() != null) {
                Toast.makeText(this, "Scan error: " + result.getErrorCorrectionLevel(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Aucun code-barres capturé, le scan a échoué.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }

    private void setupLoginButton() {
        binding.login.setOnClickListener(v -> {
            String email = binding.emailInput.getText().toString();
            String password = binding.passwordInput.getText().toString();
            if (validateForm(email,password)) {
                viewModel.login(email, password);
            }
        });
    }

    private void setupObservers() {
        viewModel.getLoginStatus().observe(this, isSuccess -> {
            if (isSuccess) {
                saveLoginState(true);
                navigateToDashboard();
            }
        });
        viewModel.getLoginErrorMessage().observe(this,errorMsg->{
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
        });
    }

    private void saveLoginState(boolean isLoggedIn) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putBoolean("isLoggedIn", isLoggedIn);
        myEdit.apply();
    }

    private boolean validateForm(String email, String password) {
        boolean isValid = true;
        if (email.isEmpty()) {
            binding.emailLayout.setError("Adresse e-mail requise");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailLayout.setError("Format d'e-mail invalide");
            isValid = false;
        } else {
            binding.emailLayout.setError(null);
        }

        if (password.isEmpty()) {
            binding.pwdLayout.setError("Mot de passe requis");
            isValid = false;
        } else {
            binding.pwdLayout.setError(null);
        }

        return isValid;
    }

    private void setupTextWatchers() {
        binding.emailInput.addTextChangedListener(new GenericTextWatcher(binding.emailLayout));
        binding.passwordInput.addTextChangedListener(new GenericTextWatcher(binding.pwdLayout));
    }

    private void navigateToDashboard() {
        Intent intent = new Intent(AuthActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    private static class GenericTextWatcher implements TextWatcher {
        private final TextInputLayout layout;

        GenericTextWatcher(com.google.android.material.textfield.TextInputLayout layout) {
            this.layout = layout;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            layout.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }


}
