package com.shaobing.runner.Bean;

import java.io.Serializable;

/**
 * @className : UserSubBean
 * @description : mysql上的数据实体
 * @date : 2020/6/20 13:12
 * @author : 邵文炳
 */
public class UserSubBean implements Serializable {

    private String userSubId;
    private String userId;
    private String courseId;

    public UserSubBean() {
    }

    public UserSubBean(String userSubId, String userId, String courseId) {
        this.userSubId = userSubId;
        this.userId = userId;
        this.courseId = courseId;
    }

    public String getUserSubId() {
        return userSubId;
    }

    public void setUserSubId(String userSubId) {
        this.userSubId = userSubId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
