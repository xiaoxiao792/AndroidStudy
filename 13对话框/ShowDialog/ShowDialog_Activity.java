package com.ShowDialog;
import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast; 
public class ShowDialog_Activity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//应用默认的布局main.xml
        Button mybutton = (Button)findViewById(R.id.button1);//显示id为“button1”的按钮，其定义见main.xml
        //String pw = findViewById(R.id.password_edit).toString();//密码
        mybutton.setOnClickListener(new OnClickListener(){ //侦听单击此按钮的事件
          public void onClick(View v) {//单击此按钮后显示对话框。对话框的布局定义在res\dialogshow.xml中
           	 LayoutInflater usingdialoglayoutxml = LayoutInflater.from(ShowDialog_Activity.this);//这里的参数ShowDialog_Activity为类名
           	 final View myviewondialog = usingdialoglayoutxml.inflate(R.layout.dialogshow, null);//按照设定的布局显示对话框
                	AlertDialog mydialoginstance = new AlertDialog.Builder(ShowDialog_Activity.this) //这里的参数ShowDialog_Activity为类名
                		.setIcon(R.drawable.icon)//图标，显示在对话框标题左侧
                		.setTitle("用户登录界面")  //对话框标题
                		.setView(myviewondialog)//注意参数为上面定义的View实例名，意为显示R.layout.dialogshow.xml这个布局文件
                		.setPositiveButton("登录", new DialogInterface.OnClickListener() { //“确定”按钮对应的功能
                			public void onClick(DialogInterface dialog, int whichButton) {//侦听是否有单击这个确定按钮
                				Toast.makeText(getApplicationContext(), "感谢您输入了信息，再见", Toast.LENGTH_LONG).show();
                			}})
                		.setNegativeButton("退出", new DialogInterface.OnClickListener() { //“结束”按钮对应的功能
                			public void onClick(DialogInterface dialog, int whichButton) {  //侦听是否有单击这个取消按钮
                				ShowDialog_Activity.this.finish();//退出程序
                			}})
                		.create(); 
                	mydialoginstance.show(); //显示对话框
                }
        });
    }
}