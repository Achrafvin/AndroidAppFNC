package com.ambapharm.ui.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ambapharm.data.dao.UserDao;
import com.ambapharm.data.entities.User;


/**
 * ViewModel for managing authentication processes.
 * It handles user login operations using identity and password or barcode data, and maintains login status,
 * error messages, and information about the logged-in user.
 */
public class AuthViewModel extends ViewModel {

    private MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private MutableLiveData<String> loginErrorMessage = new MutableLiveData<>();
    private MutableLiveData<User> loggedInUser = new MutableLiveData<>();
    private UserDao userDao;


    /**
     * Constructor for AuthViewModel.
     *
     * @param userDao The data access object (DAO) for performing user authentication operations.
     */
    public AuthViewModel(UserDao userDao) {
        this.userDao = userDao;
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
    public MutableLiveData<String> getLoginErrorMessage() {
        return loginErrorMessage;
    }



    /**
     * Attempts to log in a user with the provided email and password.
     * It queries the UserDao for a user with the given credentials and updates the login status and user data accordingly.
     *
     * @param email    The user's email.
     * @param password The user's password.
     */
    public void login(String identity, String password) {
        new Thread(() -> {
            User user = userDao.findByIdentityAndPassword(identity, password);
            if (user != null) {
                loggedInUser.postValue(user);
                loginStatus.postValue(true);
            } else {
                loginErrorMessage.postValue("Invalid identity or password");
                loginStatus.postValue(false);
            }
        }).start();

    }

//    public void loginWithBarcode(String barcodeData) {
//        if (barcodeData.equals("ABC-1234")) {
//            loggedInUser.setValue(new User("John Doe"));
//            loginStatus.setValue(true);
//        } else {
//            loginErrorMessage.setValue("Barcode invalide");
//            loginStatus.setValue(false);
//        }
//    }
}
