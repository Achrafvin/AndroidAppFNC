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


/**
 * Activity for handling user authentication via identity (Identity and password) or barcode.
 * If the user is already logged in, they are redirected to the dashboard. Otherwise, the user can
 * log in using their credentials or scan a barcode for authentication.
 */
public class AuthenticationActivity extends BaseActivity {

    private ActivityAuthenticationBinding binding;
    private AuthViewModel viewModel;


    /**
     * Called when the activity is starting. This method sets up the activity and database, and observes the ViewModel.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
        initializeDatabase();
        observeViewModel();
    }


    /**
     * Initializes the activity by setting up the login button, text watchers, and checking if the user is logged in.
     */
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

    /**
     * Checks if the user is already logged in.
     *
     * @return true if the user is logged in, false otherwise.
     */
    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }


    /**
     * Navigates to the DashboardActivity.
     */
    private void navigateToDashboard() {
        Intent intent = new Intent(AuthenticationActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Saves the logged-in user's information to SharedPreferences.
     *
     * @param user The user object containing the user's information.
     */
    private void saveUserInfo(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", user.getName());
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }


    /**
     * Initializes the in-memory database and sets up the ViewModel.
     */
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

    /**
     * Observes changes in the ViewModel for user login status and login error messages.
     */
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


    /**
     * Sets up the login button with a click listener to process user login.
     */
    private void setupLoginButton() {
        binding.login.setOnClickListener(v -> {
            String identity = Objects.requireNonNull(binding.identityInput.getText()).toString();
            String password = Objects.requireNonNull(binding.passwordInput.getText()).toString();
            if (validateForm(identity, password)) {
                viewModel.login(identity, password);
            }
        });
    }

    /**
     * Validates the login form input.
     *
     * @param identity    The identity entered by the user.
     * @param password The password entered by the user.
     * @return true if the form is valid, false otherwise.
     */
    private boolean validateForm(String identity, String password) {
        boolean isValid = true;
        isValid = validateIdentity(identity) && isValid;
        isValid = validatePassword(password) && isValid;
        return isValid;
    }

    /**
     * Validates the email input.
     *
     * @param identity The email entered by the user.
     * @return true if the email is valid, false otherwise.
     */
    private boolean validateIdentity(String identity) {
        if (identity.isEmpty()) {
            binding.identityLayout.setError("Identifiant requise");
            return false;
        }else {
            binding.identityLayout.setError(null);
            return true;
        }
    }


    /**
     * Validates the password input.
     *
     * @param password The password entered by the user.
     * @return true if the password is valid, false otherwise.
     */
    private boolean validatePassword(String password) {
        if (password.isEmpty()) {
            binding.pwdLayout.setError("Mot de passe requis");
            return false;
        } else {
            binding.pwdLayout.setError(null);
            return true;
        }
    }


    /**
     * Sets up text watchers for the email and password input fields.
     */
    private void setupTextWatchers() {
        binding.identityInput.addTextChangedListener(new GenericTextWatcher(binding.identityLayout));
        binding.passwordInput.addTextChangedListener(new GenericTextWatcher(binding.pwdLayout));
    }


    /**
     * A TextWatcher class for handling text changes in input fields.
     */
    private static class GenericTextWatcher implements TextWatcher {

        private final TextInputLayout layout;

        public GenericTextWatcher(TextInputLayout layout) {
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
