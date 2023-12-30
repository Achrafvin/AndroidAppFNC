package com.ambapharm.ui.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ambapharm.data.entities.User;

public class AuthViewModel extends ViewModel {

    private MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private MutableLiveData<String> loginErrorMessage = new MutableLiveData<>();
    private MutableLiveData<User> loggedInUser = new MutableLiveData<>();

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
        if (!email.equals("user@example.com") || !password.equals("123456")) {
            loginErrorMessage.setValue("Adresse e-mail ou mot de passe incorrect");
            loginStatus.setValue(false);
        } else {
            loggedInUser.setValue(new User("John Doe"));
            loginStatus.setValue(true);
        }

    }

    public void loginWithBarcode(String barcodeData) {
        if (barcodeData.equals("ABC-1234")) {
            loggedInUser.setValue(new User("John Doe"));
            loginStatus.setValue(true);
        } else {
            loginErrorMessage.setValue("Barcode invalide");
            loginStatus.setValue(false);
        }
    }
}
