package com.ambapharm.ui.viewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.ambapharm.data.dao.UserDao;

public class AuthViewModelFactory implements ViewModelProvider.Factory{
    private final UserDao userDao;

    public AuthViewModelFactory(UserDao userDao) {
        this.userDao = userDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AuthViewModel.class)) {
            return (T) new AuthViewModel(userDao);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
