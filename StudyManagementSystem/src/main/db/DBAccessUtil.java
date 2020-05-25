package com.example.studymanagementsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.example.studymanagementsystem.db.DBConstant.*;
import com.example.studymanagementsystem.bean.UserBean;

public class DBAccessUtil {
    private SQLiteDatabase db;
    private Context mcontext;
    private DBOpenHelper dbOpenHelper;

    public DBAccessUtil(Context context) {
        mcontext = context;
    }

    public void open() throws SQLiteException {
        // 创建一个DBOpenHelper实例
        dbOpenHelper = new DBOpenHelper(mcontext, DBConstant.DB_NAME, null, DBConstant.DB_VERSION);
        // 通过dbOpenHelper.getWritableDatabase()或者dbOpenHelper.getReadableDatabase()
        //创建或打开一个数据库SQLiteDatabase实例，其中dbOpenHelper.getWritableDatabase()
        //得到的数据库具有读写的权限，而dbOpenHelper.getReadableDatabase()得到的数据库则具有只读的权限。
        try {
            db = dbOpenHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = dbOpenHelper.getReadableDatabase();
        }
    }

    // 关闭数据库
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    public long insert(UserBean userBean) {
        // ContentValues类存储了一组键值对
        ContentValues values = new ContentValues();

        values.put(UserConstant.USER_ID, userBean.getUserid());
        values.put(UserConstant.ROLE,userBean.getRole());
        values.put(UserConstant.USER_NAME,userBean.getUsername());
        values.put(UserConstant.SEX,userBean.getSex());
        values.put(UserConstant.USER_CARD_ID,userBean.getUsercardid());
        values.put(UserConstant.COLLEGE,userBean.getCollege());
        values.put(UserConstant.DEPARTMENT,userBean.getDepartment());
        values.put(UserConstant.PHONE,userBean.getPhone());
        values.put(UserConstant.EMAIL,userBean.getEmail());
        values.put(UserConstant.PASSWORD,userBean.getPassword());
        values.put(UserConstant.ADMISSION_TIME,userBean.getAdmissionTime());
        values.put(UserConstant.MAJOR,userBean.getMajor());
        values.put(UserConstant.CALSSES,userBean.getClasses());
        values.put(UserConstant.GRADE,userBean.getGrade());

        long key = db.insert(UserConstant.USER_TABLE_NAME, null, values);

        return key;
    }

    public int update(String userId, String newPassword, String oldPassword) {
        int key = 0;
        if (oldPassword.equals(query(userId))) {
            ContentValues values = new ContentValues();
            values.put("password", newPassword);
            key = db.update(UserConstant.USER_TABLE_NAME, values, UserConstant.USER_ID + "=?", new String[]{userId});
        }
        return key;
    }

    public String query(String userId) {
        String key = "";
        Cursor c=db.query(UserConstant.USER_TABLE_NAME, new String[]{UserConstant.PASSWORD}, UserConstant.USER_ID+"=?", new String[]{userId},null, null, null);
        if(c.moveToNext()) {
            key = c.getString(c.getColumnIndex(UserConstant.PASSWORD));
        }
        return key;
    }


}
