package com.shaobing.homework05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShowDialogActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dialog);
        Button mybutton = (Button)findViewById(R.id.button1);
        mybutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                LayoutInflater usingdialoglayoutxml = LayoutInflater.from(ShowDialogActivity.this);//这里的参数ShowDialog_Activity为类名
                final View myviewondialog = usingdialoglayoutxml.inflate(R.layout.dialogshow, null);//按照设定的布局显示对话框
                AlertDialog mydialoginstance = new AlertDialog.Builder(ShowDialogActivity.this) //这里的参数ShowDialog_Activity为类名
                        .setIcon(R.drawable.icon)//图标，显示在对话框标题左侧
                        .setTitle("用户登录界面")  //对话框标题
                        .setView(myviewondialog)//注意参数为上面定义的View实例名，意为显示R.layout.dialogshow.xml这个布局文件
                        .setPositiveButton("登录", new DialogInterface.OnClickListener() { //“确定”按钮对应的功能
                            public void onClick(DialogInterface dialog, int whichButton) {
                                EditText username_edit = (EditText)myviewondialog.findViewById(R.id.username_edit);
                                EditText password_edit = (EditText)myviewondialog.findViewById(R.id.password_edit);
                                String user = username_edit.getText().toString().trim();
                                String pwd = password_edit.getText().toString().trim();
                                if (user.equals("zheng")&&pwd.equals("123456")){
                                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_LONG).show();
                                }
                            }})
                        .setNegativeButton("退出", new DialogInterface.OnClickListener() { //“结束”按钮对应的功能
                            public void onClick(DialogInterface dialog, int whichButton) {  //侦听是否有单击这个取消按钮
                                ShowDialogActivity.this.finish();//退出程序
                            }})
                        .create();
                mydialoginstance.show(); //显示对话框
            }
        });

    }
}
