package com.shaobing.runner.Bean;

import java.io.Serializable;

/**
 * @className : UserBean
 * @description : mysql上的数据实体
 * @date : 2020/6/20 13:12
 * @author : 邵文炳
 */
public class UserBean implements Serializable {

    private String userId;
    private String userPassword;
    private String userName;
    private String userImg;
    private double userHeight;
    private double userWeight;
    private int userAge;
    private int userSex;

    public UserBean() {
    }

    public UserBean(String userId, String userPassword, String userName, String userImg, double userHeight, double userWeight, int userAge, int userSex) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userImg = userImg;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
        this.userAge = userAge;
        this.userSex = userSex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public double getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(double userHeight) {
        this.userHeight = userHeight;
    }

    public double getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(double userWeight) {
        this.userWeight = userWeight;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }
}
