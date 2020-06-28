package com.shaobing.runner.Bean;

import java.io.Serializable;

/**
 * @className : DayStepBean
 * @description : sqlite上的数据实体
 * @date : 2020/6/20 13:10
 * @author : 邵文炳
 */
public class DayStepBean implements Serializable {

    private String dayStepId;
    private int dayStepNum;
    private String userId;

    public DayStepBean() {
    }

    public DayStepBean(String dayStepId, int dayStepNum, String userId) {
        this.dayStepId = dayStepId;
        this.dayStepNum = dayStepNum;
        this.userId = userId;
    }

    public String getDayStepId() {
        return dayStepId;
    }

    public void setDayStepId(String dayStepId) {
        this.dayStepId = dayStepId;
    }

    public int getDayStepNum() {
        return dayStepNum;
    }

    public void setDayStepNum(int dayStepNum) {
        this.dayStepNum = dayStepNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
