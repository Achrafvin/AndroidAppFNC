package com.gapharma.data.api.responses;

import com.gapharma.data.entities.User;

public class UserResponse {

    private final boolean success;
    private String token;
    private String message;
    private User user;

    public UserResponse(boolean success, String token, String message) {
        this.success = success;
        this.token = token;
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

