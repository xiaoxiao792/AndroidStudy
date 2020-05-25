package com.example.studymanagementsystem.bean;

public class UserBean {

    private String userId;
    private String sex;
    private String role;
    private String userName;
    private String userCardId;
    private String college;
    private String department;
    private String phone;
    private String email;
    private String password;
    private String admissionTime;
    private String major;
    private String classes;
    private String grade;

    public String getUserid() {
        return userId;
    }

    public void setUserid(String userId) {
        this.userId = userId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getUsercardid() {
        return userCardId;
    }

    public void setUsercardid(String userCardId) {
        this.userCardId = userCardId;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(String admissionTime) {
        this.admissionTime = admissionTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userId='" + userId + '\'' +
                ", sex='" + sex + '\'' +
                ", role='" + role + '\'' +
                ", userName='" + userName + '\'' +
                ", userCardId='" + userCardId + '\'' +
                ", college='" + college + '\'' +
                ", department='" + department + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", admissionTime='" + admissionTime + '\'' +
                ", major='" + major + '\'' +
                ", classes='" + classes + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public UserBean(String userId, String sex, String role, String userName, String userCardId, String college, String department, String phone, String email, String password, String admissionTime, String major, String classes, String grade) {
        this.userId = userId;
        this.sex = sex;
        this.role = role;
        this.userName = userName;
        this.userCardId = userCardId;
        this.college = college;
        this.department = department;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.admissionTime = admissionTime;
        this.major = major;
        this.classes = classes;
        this.grade = grade;
    }

    public UserBean() {
    }
}
