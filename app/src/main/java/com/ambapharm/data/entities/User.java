package com.ambapharm.data.entities;

public class User {
    private int id;
    private String name;
    private String email;
    private String emailGroup;
    private String password;
    private String cip;
    private int accessRightId;

    public User(int id, String name, String email, String emailGroup, String password, String cip, int accessRightId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.emailGroup = emailGroup;
        this.password = password;
        this.cip = cip;
        this.accessRightId = accessRightId;
    }

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailGroup() {
        return emailGroup;
    }

    public void setEmailGroup(String emailGroup) {
        this.emailGroup = emailGroup;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public int getAccessRightId() {
        return accessRightId;
    }

    public void setAccessRightId(int accessRightId) {
        this.accessRightId = accessRightId;
    }
}
