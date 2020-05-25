package com.example.studymanagementsystem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.studymanagementsystem.db.DBConstant.*;

public class DBOpenHelper extends SQLiteOpenHelper {

    private String sql_create_user = "CREATE TABLE "
            + UserConstant.USER_TABLE_NAME + "(" + UserConstant.USER_ID + " text primary key,"
            + UserConstant.ROLE + " text not null," + UserConstant.USER_NAME + " text not null,"
            + UserConstant.SEX + " text not null," + UserConstant.USER_CARD_ID + " text not null,"
            + UserConstant.COLLEGE + " text not null," + UserConstant.DEPARTMENT + " text not null,"
            + UserConstant.PHONE + " text," + UserConstant.EMAIL + " text,"
            + UserConstant.PASSWORD + " text not null," + UserConstant.ADMISSION_TIME + " text,"
            + UserConstant.MAJOR + " text," + UserConstant.CALSSES + " text,"
            + UserConstant.GRADE + " text);";

    public DBOpenHelper(@Nullable Context context, @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql_create_user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
