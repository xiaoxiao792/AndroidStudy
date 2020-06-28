package com.shaobing.runner.Bean;

import java.io.Serializable;

/**
 * @className : ExerciseBean
 * @description : mysql上的数据实体
 * @date : 2020/6/20 13:11
 * @author : 邵文炳
 */
public class ExerciseBean implements Serializable {

    private String exerciseId;
    private String userId;
    private String exerciseDate;
    private int exerciseNum;
    private int exerciseTime;

    public ExerciseBean() {
    }

    public ExerciseBean(String exerciseId, String userId, String exerciseDate, int exerciseNum, int exerciseTime) {
        this.exerciseId = exerciseId;
        this.userId = userId;
        this.exerciseDate = exerciseDate;
        this.exerciseNum = exerciseNum;
        this.exerciseTime = exerciseTime;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(String exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public int getExerciseNum() {
        return exerciseNum;
    }

    public void setExerciseNum(int exerciseNum) {
        this.exerciseNum = exerciseNum;
    }

    public int getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(int exerciseTime) {
        this.exerciseTime = exerciseTime;
    }
}
