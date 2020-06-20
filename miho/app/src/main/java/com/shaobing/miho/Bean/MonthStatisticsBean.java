package com.shaobing.miho.Bean;

import java.io.Serializable;

public class MonthStatisticsBean implements Serializable {

    private String monthStatisticsId;
    private String userId;
    private int month;
    private int planNum;
    private int clockInNum;
    private int clockOutNum;
    private int duration;

    public String getMonthStatisticsId() {
        return monthStatisticsId;
    }

    public void setMonthStatisticsId(String monthStatisticsId) {
        this.monthStatisticsId = monthStatisticsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
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

    public MonthStatisticsBean() {
    }

    public MonthStatisticsBean(String monthStatisticsId, String userId, int month, int planNum, int clockInNum, int clockOutNum, int duration) {
        this.monthStatisticsId = monthStatisticsId;
        this.userId = userId;
        this.month = month;
        this.planNum = planNum;
        this.clockInNum = clockInNum;
        this.clockOutNum = clockOutNum;
        this.duration = duration;
    }
}
