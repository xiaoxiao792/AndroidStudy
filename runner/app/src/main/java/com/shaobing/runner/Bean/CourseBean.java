package com.shaobing.runner.Bean;

import java.io.Serializable;

/**
 * @className : CourseBean
 * @description : mysql上的数据实体
 * @date : 2020/6/20 13:08
 * @author : 邵文炳
 */
public class CourseBean implements Serializable {

    private String courseId;
    private String courseName;
    private String courseImg;
    private String courseVideo;
    private int courseTime;
    private String courseDescribe;
    private int courseSubNum;

    public CourseBean() {
    }

    public CourseBean(String courseId, String courseName, String courseImg, String courseVideo, int courseTime, String courseDescribe, int courseSubNum) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseImg = courseImg;
        this.courseVideo = courseVideo;
        this.courseTime = courseTime;
        this.courseDescribe = courseDescribe;
        this.courseSubNum = courseSubNum;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public String getCourseVideo() {
        return courseVideo;
    }

    public void setCourseVideo(String courseVideo) {
        this.courseVideo = courseVideo;
    }

    public int getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(int courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseDescribe() {
        return courseDescribe;
    }

    public void setCourseDescribe(String courseDescribe) {
        this.courseDescribe = courseDescribe;
    }

    public int getCourseSubNum() {
        return courseSubNum;
    }

    public void setCourseSubNum(int courseSubNum) {
        this.courseSubNum = courseSubNum;
    }
}
