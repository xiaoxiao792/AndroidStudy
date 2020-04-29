package com.example.homework04;

public class Course {
    public int id ;
    public String name  ;
    public String obj ;
    public String phone ;
    @Override
    public String toString()
    {
        String result = "课程号 : "+this.id+","
                +"课程名:"+this.name+","
                +"上课对象:"+this.obj+","
                +"班长手机:"+this.phone;
        return result;
    }

}