package com.shaobing.miho.Bean;

import java.io.Serializable;

public class UserBean implements Serializable {
    private String userId;
    private String email;
    private String password;
    private String registerTime;
    private int userLevel;
    private String phone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserBean() {
    }

    public UserBean(String userId, String email, String password, String registerTime, int userLevel, String phone) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.registerTime = registerTime;
        this.userLevel = userLevel;
        this.phone = phone;
    }
}
