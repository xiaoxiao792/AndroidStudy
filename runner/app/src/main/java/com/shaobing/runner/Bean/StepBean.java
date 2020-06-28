package com.shaobing.runner.Bean;

import java.io.Serializable;

/**
 * @className : StepBean
 * @description : mysql上的数据实体
 * @date : 2020/6/20 13:11
 * @author : 邵文炳
 */
public class StepBean implements Serializable {

    private String stepId;
    private String userId;
    private int stepNum;
    private String stepDate;

    public StepBean() {
    }

    public StepBean(String stepId, String userId, int stepNum, String stepDate) {
        this.stepId = stepId;
        this.userId = userId;
        this.stepNum = stepNum;
        this.stepDate = stepDate;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public String getStepDate() {
        return stepDate;
    }

    public void setStepDate(String stepDate) {
        this.stepDate = stepDate;
    }
}
