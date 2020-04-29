package com.example.homework04;

public class Person
{
    public int id ;
    public String myClass  ;
    public String myName ;
    @Override
    public String toString()
    {
        String result = "学号: "+this.id+","
                +"姓名:"+this.myName+","
                +"班级:"+this.myClass;
        return result;
    }


}