package com.gapharma.ui.viewModels;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gapharma.data.dao.UserDao;
import com.gapharma.data.entities.User;
import com.gapharma.R;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * ViewModel for managing authentication processes.
 * It handles user login operations using identity and password or barcode data, and maintains login status,
 * error messages, and information about the logged-in user.
 */
public class AuthViewModel extends ViewModel {

    private final MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private final MutableLiveData<User> loggedInUser = new MutableLiveData<>();

    private final MutableLiveData<Integer> errorMessageResId = new MutableLiveData<>();
    private final UserDao userDao;

    private final CompositeDisposable disposables = new CompositeDisposable();




    /**
     * Constructor for AuthViewModel.
     *
     * @param userDao The data access object (DAO) for performing user authentication operations.
     */
    public AuthViewModel(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }


    /**
     * Gets the LiveData for the logged-in user.
     *
     * @return LiveData containing the data of the logged-in user.
     */
    public MutableLiveData<User> getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Gets the LiveData for the login status.
     *
     * @return LiveData containing the status of the login operation.
     */
    public MutableLiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }

    /**
     * Gets the LiveData for the login error message.
     *
     * @return LiveData containing the error message of the login operation.
     */


    public LiveData<Integer> getErrorMessageResId() {
        return errorMessageResId;
    }

    /**
     * Attempts to log in a user with the provided email and password.
     * It queries the UserDao for a user with the given credentials and updates the login status and user data accordingly.
     *
     * @param identity    The user's email.
     * @param password The user's password.
     */
    public void login(String identity, String password) {
      Disposable disposable = Single.fromCallable(() -> userDao.findByIdentityAndPassword(identity, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    if (user != null) {
                        loggedInUser.postValue(user);
                        loginStatus.postValue(true);
                    } else {
                        errorMessageResId.postValue(R.string.errorLogin);
                        loginStatus.postValue(false);
                    }
                }, throwable -> Log.e("LoginError", "Error logging in", throwable));
        disposables.add(disposable);
    }


    /**
     * Attempts to log in a user with the provided CIP code.
     * It queries the UserDao for a user with the given credential and updates the login status and user data accordingly.
     *
     * @param barcodeData    The user's cip code.
     */
    public void loginWithCipcode(String barcodeData) {
        Disposable disposable = Single.fromCallable(() -> userDao.findUserByCipCode(barcodeData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    if (user != null) {
                        loggedInUser.postValue(user);
                        loginStatus.postValue(true);
                    } else {
                        errorMessageResId.postValue(R.string.errorLoginCipCode);
                        loginStatus.postValue(false);
                    }
                }, throwable -> {
                    Log.e("LoginError", "Error during login with CIP code", throwable);
                });

        disposables.add(disposable);
    }


}
