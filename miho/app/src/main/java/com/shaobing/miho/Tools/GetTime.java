package com.shaobing.miho.Tools;

import java.util.*;
import java.text.*;

/**
 * @className : GetTime
 * @description : 获得与时间相关
 * @date : 2020/5/27 16:24
 * @author : 邵文炳
 */
public class GetTime{

    public static String getDate(){
        String systime = "";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
        systime = dateFormat.format(now);
        return systime;
    }

    public static String getTime(){
        String systime = "";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        systime = dateFormat.format(now);
        return systime;
    }

    private static String getUnFormatTime(){
        String systime = "";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSSS");//可以方便地修改日期格式
        systime = dateFormat.format(now);
        return systime;
    }

    public static String getAutoId(){
        StringBuffer sb = new StringBuffer();
        sb.append(getUnFormatTime());
        for (int i = 0; i < 2; i++) {
            sb.append((int)(Math.random()*9));
        }
        return sb.toString();//20位带时间的随机数
    }
}