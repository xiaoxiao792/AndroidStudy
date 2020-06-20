package com.shaobing.miho.Bean;

import java.io.Serializable;

public class PlanBean implements Serializable {

    private String planId;
    private String planName;
    private String icon;
    private String color;
    private int timeClass;
    private String beginDate;
    private String endDate;
    private int state;
    private int forceClass;
    private int duration;
    private int clockInNum;
    private int planNum;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTimeClass() {
        return timeClass;
    }

    public void setTimeClass(int timeClass) {
        this.timeClass = timeClass;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getForceClass() {
        return forceClass;
    }

    public void setForceClass(int forceClass) {
        this.forceClass = forceClass;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getClockInNum() {
        return clockInNum;
    }

    public void setClockInNum(int clockInNum) {
        this.clockInNum = clockInNum;
    }

    public int getPlanNum() {
        return planNum;
    }

    public void setPlanNum(int clockOutNum) {
        this.planNum = clockOutNum;
    }

    public PlanBean() {
    }

    public PlanBean(String planId, String planName, String icon, String color, int timeClass, String beginDate, String endDate, int state, int forceClass, int duration, int clockInNum, int planNum) {
        this.planId = planId;
        this.planName = planName;
        this.icon = icon;
        this.color = color;
        this.timeClass = timeClass;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.state = state;
        this.forceClass = forceClass;
        this.duration = duration;
        this.clockInNum = clockInNum;
        this.planNum = planNum;
    }
}
