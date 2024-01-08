package com.gapharma.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;

import com.gapharma.R;
import com.gapharma.data.DatabaseClient;
import com.gapharma.data.dao.AccessRightDao;
import com.gapharma.data.dao.UserDao;
import com.gapharma.data.entities.AccessRight;
import com.gapharma.data.entities.User;
import com.gapharma.databinding.ActivityAuthenticationBinding;
import com.gapharma.utils.GenericTextWatcher;
import com.gapharma.ui.viewModels.AuthViewModel;
import com.gapharma.ui.viewModels.AuthViewModelFactory;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.journeyapps.barcodescanner.ScanIntentResult;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Objects;



/**
 * Activity for handling user authentication via identity (Identity and password) or barcode.
 * If the user is already logged in, they are redirected to the dashboard. Otherwise, the user can
 * log in using their credentials or scan a barcode for authentication.
 */
public class AuthenticationActivity extends BaseActivity  {

    private ActivityAuthenticationBinding binding;
    private AuthViewModel viewModel;

    private final CompositeDisposable disposables = new CompositeDisposable();


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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }


    /**
     * Initializes the activity by setting up the login button, text watchers, and checking if the user is logged in.
     */
    private void initializeActivity() {
        binding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (isUserLoggedIn()) {
            navigateToDashboard();
            return;
        }
        setupLoginButton();
        setupTextWatchers();
        setupLoginWithCipCode();

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
      Disposable disposable = Single.fromCallable(() -> {
                    AccessRightDao accessRightDao = DatabaseClient.getInstance(this).getAppDatabase().accessRightDao();
                    return accessRightDao.findById(user.getAccessRightId());
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(accessRight -> {
                    SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userName", user.getName());
                    editor.putBoolean("isLoggedIn", true);
                    editor.putString("userRole", accessRight != null ? accessRight.getName() : "null");
                    editor.apply();
                }, throwable -> Log.e("AuthActivity", "Error Getting AccessRight", throwable));
        disposables.add(disposable);

    }


    /**
     * Initializes the in-memory database and sets up the ViewModel.
     */
    private void initializeDatabase() {
        UserDao userDao = DatabaseClient.getInstance(this).getAppDatabase().userDao();
        AccessRightDao accessRightDao = DatabaseClient.getInstance(this).getAppDatabase().accessRightDao();
        AccessRight admin = new AccessRight(1L, "ROLE_ADMIN");
        AccessRight user = new AccessRight(2L, "ROLE_USER");

        User testUser = new User("John", "Id1", "group@example.com", "123456", "4754P4252", admin.getId());
        User testUser2 = new User("User test2", "Id2", "group@example.com", "123456", "4754P42235", user.getId());
        User testUser3 = new User("User test3", "Id3", "group@example.com", "123456", "4754P45258", user.getId());


       Disposable disposable = Completable.fromAction(() -> {
                    accessRightDao.save(admin);
                    accessRightDao.save(user);
                    userDao.save(testUser);
                    userDao.save(testUser2);
                    userDao.save(testUser3);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                }, throwable -> Log.e("DatabaseInit", "Error initializing database", throwable));

        AuthViewModelFactory factory = new AuthViewModelFactory(userDao);
        viewModel = new ViewModelProvider(this, factory).get(AuthViewModel.class);

        disposables.add(disposable);
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
        viewModel.getErrorMessageResId().observe(this, resId -> {
            if (resId != null && resId != 0) {
                Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
            }
        });

    }


    /**
     * Sets up the login button with a click listener to process user login.
     */
    private void setupLoginButton() {
        binding.login.setOnClickListener(v -> {
            hideKeyboard();
            String identity = Objects.requireNonNull(binding.identityInput.getText()).toString();
            String password = Objects.requireNonNull(binding.passwordInput.getText()).toString();
            if (isFormValid()) {
                viewModel.login(identity, password);
            }
        });
    }

    private boolean isFormValid() {
        return validateField(binding.identityInput, binding.identityLayout, R.string.errorIdentity) && validateField(binding.passwordInput, binding.pwdLayout, R.string.errorPassword);
    }

    private boolean validateField(TextInputEditText field, TextInputLayout layout, int errorMessageResId) {
        if (Objects.requireNonNull(field.getText()).toString().isEmpty()) {
            layout.setError(getString(errorMessageResId));
            return false;
        }
        layout.setError(null);
        return true;
    }


    private void setupLoginWithCipCode() {
        binding.imageView.setOnClickListener(v -> scanBarcode());
    }


    /**
     * Called when a barcode is scanned for LogIn.
     *
     * @param result The result of the barcode scan.
     */
    @Override
    protected void onBarcodeScanned(ScanIntentResult result) {
        if (result.getContents() != null) {
            viewModel.loginWithCipcode(result.getContents());
        } else {
            String errorMessage = result.getErrorCorrectionLevel() != null
                    ? "Scan error: " + result.getErrorCorrectionLevel()
                    : getString(R.string.errorScan); // Assuming you have a string resource for scan failure
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Sets up text watchers for the email and password input fields.
     */
    private void setupTextWatchers() {
        binding.identityInput.addTextChangedListener(new GenericTextWatcher(binding.identityLayout));
        binding.passwordInput.addTextChangedListener(new GenericTextWatcher(binding.pwdLayout));
    }

}
