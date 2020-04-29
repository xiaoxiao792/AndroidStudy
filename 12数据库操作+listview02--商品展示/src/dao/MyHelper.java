package cn.itcast.product.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
	// 由于父类没有无参构造函数, 所以子类必须指定调用父类哪个有参的构造函数
	public MyHelper(Context context) {
		super(context, "itcast.db", null, 2);
	}

	public void onCreate(SQLiteDatabase db) {
		System.out.println("onCreate");
		db.execSQL("CREATE TABLE account(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name VARCHAR(20)," + // 姓名列
				"balance INTEGER)"); // 金额列
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("onUpgrade");
	}
}
