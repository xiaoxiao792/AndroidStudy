package com.shaobing.runner.Bean;

import java.io.Serializable;

/**
 * @className : DayExerciseBean
 * @description : sqlite上的数据实体
 * @date : 2020/6/20 13:09
 * @author : 邵文炳
 */
public class DayExerciseBean implements Serializable {

    private String dayExerciseId;
    private String userId;
    private int dayExerciseNUm;
    private int dayExerciseTime;

    public DayExerciseBean() {
    }

    public DayExerciseBean(String dayExerciseId, String userId, int dayExerciseNUm, int dayExerciseTime) {
        this.dayExerciseId = dayExerciseId;
        this.userId = userId;
        this.dayExerciseNUm = dayExerciseNUm;
        this.dayExerciseTime = dayExerciseTime;
    }

    public String getDayExerciseId() {
        return dayExerciseId;
    }

    public void setDayExerciseId(String dayExerciseId) {
        this.dayExerciseId = dayExerciseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDayExerciseNUm() {
        return dayExerciseNUm;
    }

    public void setDayExerciseNUm(int dayExerciseNUm) {
        this.dayExerciseNUm = dayExerciseNUm;
    }

    public int getDayExerciseTime() {
        return dayExerciseTime;
    }

    public void setDayExerciseTime(int dayExerciseTime) {
        this.dayExerciseTime = dayExerciseTime;
    }
}
