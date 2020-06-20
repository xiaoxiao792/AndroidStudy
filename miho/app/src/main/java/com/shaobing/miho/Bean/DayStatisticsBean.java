package com.shaobing.miho.Bean;

import java.io.Serializable;

public class DayStatisticsBean implements Serializable {

    private String dayStatisticsId;
    private String userId;
    private String date;
    private int planNum;
    private int clockInNum;
    private int clockOutNum;
    private int duration;

    public String getDayStatisticsId() {
        return dayStatisticsId;
    }

    public void setDayStatisticsId(String dayStatisticsId) {
        this.dayStatisticsId = dayStatisticsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPlanNum() {
        return planNum;
    }

    public void setPlanNum(int planNum) {
        this.planNum = planNum;
    }

    public int getClockInNum() {
        return clockInNum;
    }

    public void setClockInNum(int clockInNum) {
        this.clockInNum = clockInNum;
    }

    public int getClockOutNum() {
        return clockOutNum;
    }

    public void setClockOutNum(int clockOutNum) {
        this.clockOutNum = clockOutNum;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public DayStatisticsBean() {
    }

    public DayStatisticsBean(String dayStatisticsId, String userId, String date, int planNum, int clockInNum, int clockOutNum, int duration) {
        this.dayStatisticsId = dayStatisticsId;
        this.userId = userId;
        this.date = date;
        this.planNum = planNum;
        this.clockInNum = clockInNum;
        this.clockOutNum = clockOutNum;
        this.duration = duration;
    }
}
