package com.gapharma.data.entities;

import androidx.room.*;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;
    private String email;
    private String emailGroup;
    private String password;
    private String cip;
    private Long accessRightId;


    public User(String name, String email, String emailGroup, String password, String cip, Long accessRightId) {
        this.name = name;
        this.email = email;
        this.emailGroup = emailGroup;
        this.password = password;
        this.cip = cip;
        this.accessRightId = accessRightId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getAccessRightId() {
        return accessRightId;
    }

    public void setAccessRightId(Long accessRightId) {
        this.accessRightId = accessRightId;
    }
}
