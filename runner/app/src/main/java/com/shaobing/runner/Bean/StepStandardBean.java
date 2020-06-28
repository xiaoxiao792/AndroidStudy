package com.shaobing.runner.Bean;

import java.io.Serializable;

/**
 * @className : StepStandardBean
 * @description : sqlite上的数据实体
 * @date : 2020/6/20 13:12
 * @author : 邵文炳
 */
public class StepStandardBean implements Serializable {

    private String stepStandardId;
    private String userId;
    private int stepStandardNum;

    public StepStandardBean() {
    }

    public StepStandardBean(String stepStandardId, String userId, int stepStandardNum) {
        this.stepStandardId = stepStandardId;
        this.userId = userId;
        this.stepStandardNum = stepStandardNum;
    }

    public String getStepStandardId() {
        return stepStandardId;
    }

    public void setStepStandardId(String stepStandardId) {
        this.stepStandardId = stepStandardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStepStandardNum() {
        return stepStandardNum;
    }

    public void setStepStandardNum(int stepStandardNum) {
        this.stepStandardNum = stepStandardNum;
    }
}
