package cn.itcast.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Activity01 extends Activity {
	// ��activity��������ʱ����õķ���.
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity01);
		Log.i("Activity01", "onCreate()");
	}
	// �����activity����û��ɼ���ʱ�� ���õķ���.
	protected void onStart() {
		super.onStart();
		Log.i("Activity01", "onStart()");
	}
	protected void onRestart() {
		super.onRestart();
		Log.i("Activity01", "onRestart()");
	}
	// ��activity��ȡ�������ʱ�� ���õķ���.
	protected void onResume() {
		super.onResume();
		Log.i("Activity01", "onResume()");
	}
	// ��activityʧȥ�����ʱ�� ���õķ���
	protected void onPause() {
		super.onPause();
		Log.i("Activity01", "onPause()");
	}
	// ��activity�û����ɼ���ʱ�� ���õķ���.
	protected void onStop() {
		super.onStop();
		Log.i("Activity01", "onStop()");
	}
	// ��activity�����ٵ�ʱ�� ���õķ���.
	protected void onDestroy() {
		super.onDestroy();
		Log.i("Activity01", "onDestroy()");
	}
    //�����а�ť�ĵ���¼�
	public void click(View view) {
          //����һ��Intent����,ͨ���ö�������2��Activity
		Intent intent = new Intent(this, Activity02.class);
		startActivity(intent);
	}
}
