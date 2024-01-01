package com.ambapharm.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.ambapharm.data.AppDatabase;
import com.ambapharm.data.dao.UserDao;
import com.ambapharm.data.entities.User;
import com.ambapharm.databinding.ActivityAuthenticationBinding;
import com.ambapharm.ui.viewModels.AuthViewModel;
import com.ambapharm.ui.viewModels.AuthViewModelFactory;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AuthenticationActivity extends BaseActivity {

    private ActivityAuthenticationBinding binding;
    private AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
        initializeDatabase();
        observeViewModel();
    }

    private void initializeActivity() {
        if (isUserLoggedIn()) {
            navigateToDashboard();
            return;
        }

        binding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupLoginButton();
        setupTextWatchers();

        binding.imageView.setOnClickListener(v -> scanBarcode());
    }

    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }

    private void navigateToDashboard() {
        Intent intent = new Intent(AuthenticationActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveUserInfo(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", user.getName());
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    private void initializeDatabase() {
        AppDatabase db = Room.inMemoryDatabaseBuilder(getApplicationContext(), AppDatabase.class).build();
        UserDao userDao = db.userDao();

        User testUser = new User("John", "Id1", "group@example.com", "123456", "4754P4252", 1L);
        User testUser2 = new User("User test2", "Id2", "group@example.com", "123456", "4754P42235", 1L);
        User testUser3 = new User("User test3", "Id3", "group@example.com", "123456", "4754P45258", 1L);


        new Thread(() -> {
            userDao.save(testUser);
            userDao.save(testUser2);
            userDao.save(testUser3);
        }).start();

        AuthViewModelFactory factory = new AuthViewModelFactory(userDao);
        viewModel = new ViewModelProvider(this, factory).get(AuthViewModel.class);
    }


    private void observeViewModel() {
        viewModel.getLoggedInUser().observe(this, user -> {
            if (user != null) {
                saveUserInfo(user);
                navigateToDashboard();
            }
        });

        viewModel.getLoginErrorMessage().observe(this, errorMsg ->
                Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show());
    }

    private void setupLoginButton() {
        binding.login.setOnClickListener(v -> {
            String email = Objects.requireNonNull(binding.emailInput.getText()).toString();
            String password = Objects.requireNonNull(binding.passwordInput.getText()).toString();
            if (validateForm(email, password)) {
                viewModel.login(email, password);
            }
        });
    }

    private boolean validateForm(String email, String password) {
        boolean isValid = true;
        isValid = validateEmail(email) && isValid;
        isValid = validatePassword(password) && isValid;
        return isValid;
    }

    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            binding.emailLayout.setError("Identifiant requise");
            return false;
        }else {
            binding.emailLayout.setError(null);
            return true;
        }
    }

    private boolean validatePassword(String password) {
        if (password.isEmpty()) {
            binding.pwdLayout.setError("Mot de passe requis");
            return false;
        } else {
            binding.pwdLayout.setError(null);
            return true;
        }
    }

    private void setupTextWatchers() {
        binding.emailInput.addTextChangedListener(new GenericTextWatcher(binding.emailLayout));
        binding.passwordInput.addTextChangedListener(new GenericTextWatcher(binding.pwdLayout));
    }

    private record GenericTextWatcher(TextInputLayout layout) implements TextWatcher {

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
