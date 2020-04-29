package com.example.homework04;

public class Relation {
    public int id ;
    public long studentId  ;
    public long courseId ;
    @Override
    public String toString()
    {
        String result = "ID : "+this.id+","
                +"姓名:"+this.studentId+","
                +"班级:"+this.courseId;
        return result;
    }
}