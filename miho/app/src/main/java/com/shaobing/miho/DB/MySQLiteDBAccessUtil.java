package com.shaobing.miho.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.shaobing.miho.Bean.PlanBean;
import com.shaobing.miho.Bean.ShortCutBean;
import com.shaobing.miho.DB.DBConstant.*;
import com.shaobing.miho.Tools.GetTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @className : MySQLiteDBAccessUtil
 * @description : 
 * @date : 2020/6/3 15:17
 * @author : 邵文炳
 */
public class MySQLiteDBAccessUtil {
    private SQLiteDatabase db;
    private Context mContext;
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    public MySQLiteDBAccessUtil(Context context) {
        mContext = context;
    }

    public void open() throws SQLiteException {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(mContext, DBConstant.DB_NAME, null, DBConstant.DB_VERSION);
        try {
            db = mySQLiteOpenHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = mySQLiteOpenHelper.getReadableDatabase();
        }
    }

    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }


    public List<ShortCutBean> queryAllShortCut() {
        String sql = "select * from " + ShortCutConstant.SHORT_CUT_TABLE_NAME;
        Cursor c=db.rawQuery(sql,null);
        return ConvertToShortCutBean(c);
    }

    public void deletePlan(String planId) {
        String sql="delete from "+ PlanConstant.PLAN_TABLE_NAME + " where " + PlanConstant.PLAN_ID + " =?";
        db.execSQL(sql,new Object[]{planId});
    }

    public void planPlusClockNum(String planID){
        String sql="update "+ PlanConstant.PLAN_TABLE_NAME +" set " + PlanConstant.CLOCK_IN_NUM + " = "
                + PlanConstant.CLOCK_IN_NUM + "+1 where " + PlanConstant.PLAN_ID + " =?";
        db.execSQL(sql,new Object[]{planID});
    }

    public void planPlusPlanNum(String planID){
        String sql="update "+ PlanConstant.PLAN_TABLE_NAME +" set " + PlanConstant.PLAN_NUM + " = "
                + PlanConstant.PLAN_NUM + "+1 where " + PlanConstant.PLAN_ID + " =?";
        db.execSQL(sql,new Object[]{planID});
    }

    public void planFinishPlan(String planID){
        String sql="update "+ PlanConstant.PLAN_TABLE_NAME +" set " + PlanConstant.STATE + " = "
                + "2 where " + PlanConstant.PLAN_ID + " =?";
        db.execSQL(sql,new Object[]{planID});
    }

    public void autoPlanPlusPlanNum(List<PlanBean> planBeans){
        String planId;
        db.beginTransaction();
        try {
            for (PlanBean a : planBeans){
                planId = a.getPlanId();
                planPlusPlanNum(planId);
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    public long addPlan(PlanBean planBean,String userId){
        ContentValues values = new ContentValues();

        values.put(PlanConstant.PLAN_ID, planBean.getPlanId());
        values.put(PlanConstant.USER_ID, userId);
        values.put(PlanConstant.PLAN_NAME, planBean.getPlanName());
        values.put(PlanConstant.ICON, planBean.getIcon());
        values.put(PlanConstant.COLOR, planBean.getColor());
        values.put(PlanConstant.TIME_CLASS, planBean.getTimeClass());
        values.put(PlanConstant.BEGIN_DATE, planBean.getBeginDate());
        values.put(PlanConstant.END_DATE, planBean.getEndDate());
        values.put(PlanConstant.STATE, planBean.getState());
        values.put(PlanConstant.FORCE_CLASS, planBean.getForceClass());
        values.put(PlanConstant.DURATION, planBean.getDuration());
        values.put(PlanConstant.CLOCK_IN_NUM, planBean.getClockInNum());
        values.put(PlanConstant.PLAN_NUM, planBean.getPlanNum());

        long key = db.insert(PlanConstant.PLAN_TABLE_NAME, null, values);
        return key;
    }

    public List<PlanBean> queryRunPlan() {
        String sql = "select * from " + PlanConstant.PLAN_TABLE_NAME + " where " + PlanConstant.STATE + " = 1";
        Cursor c=db.rawQuery(sql,null);
        return ConvertToPlanBean(c);
    }

    public List<PlanBean> queryAllPlan() {
        String sql = "select * from " + PlanConstant.PLAN_TABLE_NAME;
        Cursor c=db.rawQuery(sql,null);
        return ConvertToPlanBean(c);
    }

    public List<PlanBean> queryRunPlan(String userId) {
        String sql = "select * from " + PlanConstant.PLAN_TABLE_NAME + " where " + PlanConstant.STATE
                + " = 1 and ( "+ PlanConstant.USER_ID + " = '" + userId + "' or " + PlanConstant.USER_ID + " = '10001')";
        Cursor c=db.rawQuery(sql,null);
        return ConvertToPlanBean(c);
    }

    public List<PlanBean> queryAllPlan(String userId) {
        String sql = "select * from " + PlanConstant.PLAN_TABLE_NAME + " where " + PlanConstant.USER_ID
                + " = '" + userId + "' or " + PlanConstant.USER_ID + " = '10001'";
        Cursor c=db.rawQuery(sql,null);
        return ConvertToPlanBean(c);
    }

    public boolean queryRecordState(String planId){
        boolean key = false;
        int isClockIn = 0;
        Cursor c=db.query(RecordConstant.RECORD_TABLE_NAME, new String[]{RecordConstant.IS_CLOCK_IN},
                RecordConstant.PLAN_ID + "=? and " + RecordConstant.DATE + "=?",
                new String[]{planId, GetTime.getDate()},
                null, null, null);
        if(c.moveToNext()) {
            isClockIn = c.getInt(c.getColumnIndex(RecordConstant.IS_CLOCK_IN));
        }
        if (isClockIn == 1){
            key =true;
        }
        return key;
    }

    public int updateRecord(String planId){
        int key;
        ContentValues values = new ContentValues();
        values.put(RecordConstant.IS_CLOCK_IN, 1);
        key = db.update(RecordConstant.RECORD_TABLE_NAME, values, RecordConstant.PLAN_ID + "=?", new String[]{planId});
        return key;
    }

    public void addRecord(String userId,String planId){
        String recordId;
        String date;
        String sql;
        date = GetTime.getDate();
        recordId = GetTime.getAutoId();
        sql = "insert into " + RecordConstant.RECORD_TABLE_NAME
                + " values(?,?,?,?,?)";
        db.execSQL(sql,new Object[]{recordId,planId,userId,date,0});
    }

    public void autoAddRecord(String userId,List<PlanBean> planBeans){
        String planId;
        db.beginTransaction();
        try {
            for (PlanBean a : planBeans){
//                sql = "insert into " + RecordConstant.RECORD_TABLE_NAME
//                        + " values('" + recordId + "','" + planId + "','" + userId +"','" + date + "',0)";
//                db.execSQL(sql);
                planId = a.getPlanId();
                addRecord(userId,planId);
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }

    }

    private List<ShortCutBean> ConvertToShortCutBean(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }
        List<ShortCutBean> query = new ArrayList<>();
        for (int i = 0; i < resultCounts; i++) {
            ShortCutBean shortCutBean = new ShortCutBean();
            shortCutBean.setPositionId(cursor.getString(cursor.getColumnIndex(ShortCutConstant.POSITION_ID)));
            shortCutBean.setIcon(cursor.getString(cursor.getColumnIndex(ShortCutConstant.ICON)));
            shortCutBean.setColor(cursor.getString(cursor.getColumnIndex(ShortCutConstant.COLOR)));
            query.add(shortCutBean);
            cursor.moveToNext();
        }
        return query;
    }

    private List<PlanBean> ConvertToPlanBean(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }
        List<PlanBean> query = new ArrayList<>();
        for (int i = 0; i < resultCounts; i++) {
            PlanBean planBean = new PlanBean();
            planBean.setPlanId(cursor.getString(cursor.getColumnIndex(PlanConstant.PLAN_ID)));
            planBean.setPlanName(cursor.getString(cursor.getColumnIndex(PlanConstant.PLAN_NAME)));
            planBean.setIcon(cursor.getString(cursor.getColumnIndex(PlanConstant.ICON)));
            planBean.setColor(cursor.getString(cursor.getColumnIndex(PlanConstant.COLOR)));
            planBean.setTimeClass(cursor.getInt(cursor.getColumnIndex(PlanConstant.TIME_CLASS)));
            planBean.setBeginDate(cursor.getString(cursor.getColumnIndex(PlanConstant.BEGIN_DATE)));
            planBean.setEndDate(cursor.getString(cursor.getColumnIndex(PlanConstant.END_DATE)));
            planBean.setState(cursor.getInt(cursor.getColumnIndex(PlanConstant.STATE)));
            planBean.setForceClass(cursor.getInt(cursor.getColumnIndex(PlanConstant.FORCE_CLASS)));
            planBean.setDuration(cursor.getInt(cursor.getColumnIndex(PlanConstant.DURATION)));
            planBean.setClockInNum(cursor.getInt(cursor.getColumnIndex(PlanConstant.CLOCK_IN_NUM)));
            planBean.setPlanNum(cursor.getInt(cursor.getColumnIndex(PlanConstant.PLAN_NUM)));
            query.add(planBean);
            cursor.moveToNext();
        }
        return query;
    }
}
