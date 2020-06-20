package com.shaobing.miho.Bean;

import java.io.Serializable;

public class RecordBean implements Serializable {

    private String recordId;
    private String planId;
    private String userId;
    private String date;
    private int isClockIn;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
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

    public int getIsClockIn() {
        return isClockIn;
    }

    public void setIsClockIn(int isClockIn) {
        this.isClockIn = isClockIn;
    }

    public RecordBean() {
    }

    public RecordBean(String recordId, String planId, String userId, String date, int isClockIn) {
        this.recordId = recordId;
        this.planId = planId;
        this.userId = userId;
        this.date = date;
        this.isClockIn = isClockIn;
    }
}
