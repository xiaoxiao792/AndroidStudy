package cn.itcast.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Activity01 extends Activity {
	// 当activity被创建的时候调用的方法.
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity01);
		Log.i("Activity01", "onCreate()");
	}
	// 当这个activity变成用户可见的时候 调用的方法.
	protected void onStart() {
		super.onStart();
		Log.i("Activity01", "onStart()");
	}
	protected void onRestart() {
		super.onRestart();
		Log.i("Activity01", "onRestart()");
	}
	// 当activity获取到焦点的时候 调用的方法.
	protected void onResume() {
		super.onResume();
		Log.i("Activity01", "onResume()");
	}
	// 当activity失去焦点的时候 调用的方法
	protected void onPause() {
		super.onPause();
		Log.i("Activity01", "onPause()");
	}
	// 当activity用户不可见的时候 调用的方法.
	protected void onStop() {
		super.onStop();
		Log.i("Activity01", "onStop()");
	}
	// 当activity被销毁的时候 掉用的方法.
	protected void onDestroy() {
		super.onDestroy();
		Log.i("Activity01", "onDestroy()");
	}
    //界面中按钮的点击事件
	public void click(View view) {
          //创建一个Intent对象,通过该对象开启第2个Activity
		Intent intent = new Intent(this, Activity02.class);
		startActivity(intent);
	}
}
