package com.ambapharm.ui.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ambapharm.data.dao.UserDao;
import com.ambapharm.data.entities.User;

public class AuthViewModel extends ViewModel {

    private MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private MutableLiveData<String> loginErrorMessage = new MutableLiveData<>();
    private MutableLiveData<User> loggedInUser = new MutableLiveData<>();
    private UserDao userDao;

    public AuthViewModel(UserDao userDao) {
        this.userDao = userDao;
    }

    public MutableLiveData<User> getLoggedInUser() {
        return loggedInUser;
    }
    public MutableLiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }
    public MutableLiveData<String> getLoginErrorMessage() {
        return loginErrorMessage;
    }


    public void login(String email, String password) {
        new Thread(() -> {
            User user = userDao.findByEmailAndPassword(email, password);
            if (user != null) {
                loggedInUser.postValue(user);
                loginStatus.postValue(true);
            } else {
                loginErrorMessage.postValue("Invalid email or password");
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
