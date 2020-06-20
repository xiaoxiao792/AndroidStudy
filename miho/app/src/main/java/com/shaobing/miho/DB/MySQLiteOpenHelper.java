package com.shaobing.miho.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.shaobing.miho.DB.DBConstant.*;
import com.shaobing.miho.Tools.GetTime;

/**
 * @className : MySQLiteOpenHelper
 * @description : 
 * @date : 2020/6/3 15:16
 * @author : 邵文炳
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private String SQL_CREATE_PLAN = "CREATE TABLE "
            + PlanConstant.PLAN_TABLE_NAME + "(" + PlanConstant.PLAN_ID + " text not null primary key,"
            + PlanConstant.USER_ID + " text not null,"
            + PlanConstant.PLAN_NAME + " text not null," + PlanConstant.ICON + " text not null,"
            + PlanConstant.COLOR + " text not null," + PlanConstant.TIME_CLASS + " integer not null,"
            + PlanConstant.BEGIN_DATE + " text not null," + PlanConstant.END_DATE + " text,"
            + PlanConstant.STATE + " integer not null," + PlanConstant.FORCE_CLASS + " integer not null,"
            + PlanConstant.DURATION + " integer not null," + PlanConstant.CLOCK_IN_NUM + " integer not null,"
            + PlanConstant.PLAN_NUM + " integer not null);";
    private String SQL_DEFAULT_PLAN = "insert into " + PlanConstant.PLAN_TABLE_NAME
            + " values('10001','10001','左上角Miho进入个人主页','1','#00BCD4','0','"
            + GetTime.getDate() + "','null','1','1','0','0','0'),('10002','10001','右下角+号添加新任务','1','#FFC107','3','"
            + GetTime.getDate() + "','null','1','1','0','0','0'),('10003','10001','右划删除或编辑任务','1','#00C853','3','"
            + GetTime.getDate() + "','null','1','1','0','0','0'),('10004','10001','左下角查看统计数据','1','#F06292','0','"
            + GetTime.getDate() + "','null','1','1','0','0','0'),('10005','10001','右上角扫帚进入菜单选项','1','#FF6D00','124','"
            + GetTime.getDate() + "','null','1','1','0','0','0'),('10006','10001','开始一个一分钟的任务','1','#00BCD4','124','"
            + GetTime.getDate() + "','null','1','2','1','0','0');";

    private String SQL_CREATE_RECORD = "CREATE TABLE "
            + RecordConstant.RECORD_TABLE_NAME + "(" + RecordConstant.RECORD_ID + " text not null primary key,"
            + RecordConstant.PLAN_ID + " text not null," + RecordConstant.USER_ID + " text not null,"
            + RecordConstant.DATE + " text not null," + RecordConstant.IS_CLOCK_IN + " integer not null);";

    private String SQL_CREATE_DAY_STATISTICS = "CREATE TABLE "
            + DayStatisticsConstant.DAY_STATISTICS_TABLE_NAME + "(" + DayStatisticsConstant.DAY_STATISTICS_ID + " text not null primary key,"
            + DayStatisticsConstant.USER_ID + " text not null," + DayStatisticsConstant.DATE + " text not null,"
            + DayStatisticsConstant.PLAN_NUM + " integer not null," + DayStatisticsConstant.CLOCK_IN_NUM + " integer not null,"
            + DayStatisticsConstant.CLOCK_OUT_NUM + " integer not null," + DayStatisticsConstant.DURATION + " integer not null);";

    private String SQL_CREATE_MONTH_STATISTICS = "CREATE TABLE "
            + MonthStatisticsConstant.MONTH_STATISTICS_TABLE_NAME + "(" + MonthStatisticsConstant.MONTH_STATISTICS_ID + " text not null primary key,"
            + MonthStatisticsConstant.USER_ID + " text not null," + MonthStatisticsConstant.MONTH + " integer not null,"
            + MonthStatisticsConstant.PLAN_NUM + " integer not null," + MonthStatisticsConstant.CLOCK_IN_NUM + " integer not null,"
            + MonthStatisticsConstant.CLOCK_OUT_NUM + " integer not null," + MonthStatisticsConstant.DURATION + " integer not null);";

    private String SQL_CREATE_SHORT_CUT = "CREATE TABLE "
            + ShortCutConstant.SHORT_CUT_TABLE_NAME + "(" + ShortCutConstant.POSITION_ID + " text not null primary key,"
            + ShortCutConstant.ICON + " text not null," + ShortCutConstant.COLOR + " text not null);";
    private String SQL_DEFAULT_SHORT_CUT = "insert into " + ShortCutConstant.SHORT_CUT_TABLE_NAME
            + " values('1','1','#00BCD4'),('2','2','#FFC107'),('3','3','#00C853'),('4','4','#F06292'),('5','5','#FF6D00');";

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_PLAN);
        db.execSQL(SQL_CREATE_RECORD);
        db.execSQL(SQL_CREATE_DAY_STATISTICS);
        db.execSQL(SQL_CREATE_MONTH_STATISTICS);
        db.execSQL(SQL_CREATE_SHORT_CUT);

        db.execSQL(SQL_DEFAULT_PLAN);
        db.execSQL(SQL_DEFAULT_SHORT_CUT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
