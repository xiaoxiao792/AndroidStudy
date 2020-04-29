package com.MyDialogDemo;
import android.app.Activity;
import android.app.AlertDialog;//注意需要引入
import android.app.Dialog;//注意需要引入
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;//列表对话框中需要引入
import android.content.DialogInterface.OnClickListener;//注意需要引入
import android.os.Bundle;//传参数时需要引入的类
import android.view.View;
import android.widget.Button;						
import android.widget.EditText;
public class MyDialogDemo_MainActivity extends Activity {
		final int COMMON_DIALOG = 1;//普通对话框id
		final int LIST_DIALOG = 2;//列表对话框id
		final int LIST_DIALOG_SINGLE = 3;	//单选对话框id
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);//设置默认布局，其中定义了相应的按钮
	        Button btn1 = (Button)findViewById(R.id.Button01);//获得Button1对象
	        Button btn2 = (Button)findViewById(R.id.Button02);//获得Button2对象
	        Button btn3 = (Button)findViewById(R.id.Button03);//获得Button3对象
	        btn1.setOnClickListener(new View.OnClickListener(){ //为Button1设置OnClickListener监听器
				public void onClick(View v) {
					showDialog(COMMON_DIALOG);//显示COMMON_DIALOG对话框
				}
			});
	        btn2.setOnClickListener(new View.OnClickListener(){//为Button2设置OnClickListener监听器
				public void onClick(View v) {
					showDialog(LIST_DIALOG);//显示LIST_DIALOG对话框
				}
			});
	        btn3.setOnClickListener(new View.OnClickListener(){//为Button3设置OnClickListener监听器
				public void onClick(View v) {
					showDialog(LIST_DIALOG_SINGLE);	//显示LIST_DIALOG_SINGLE对话框
				}
			});
	    }
		@Override
		protected Dialog onCreateDialog(int id) {//重写onCreateDialog方法
			Dialog dialog = null;//声明一个Dialog对象用于返回相应的对话框类型
			switch(id){		//对id进行判断
				case COMMON_DIALOG://普通对话框
					Builder b1 = new AlertDialog.Builder(this);
					b1.setIcon(R.drawable.icon);		//设置对话框的图标
					b1.setTitle(R.string.title1);			//设置对话框的标题
					b1.setMessage("普通对话框");	//设置对话框的显示内容
					b1.setPositiveButton(//添加按钮
							R.string.ok, 
							new OnClickListener() {			//为按钮添加监听器
								public void onClick(DialogInterface dialog, int which) {
									EditText et = (EditText)findViewById(R.id.EditText01);
									et.setText("您选中了普通对话框"); //设置EditText内容
								}
							});
					dialog = b1.create();			//生成Dialog对象
					break;
				case LIST_DIALOG:
					Builder b2 = new AlertDialog.Builder(this);
					b2.setIcon(R.drawable.icon);//设置对话框的图标
					b2.setTitle(R.string.title2);//设置对话框的标题
					b2.setItems(//设置列表中的各个属性，用字符串数组，里面有选项列表
						R.array.msa, new DialogInterface.OnClickListener() {	//为列表设置OnClickListener监听器
							public void onClick(DialogInterface dialog, int which) {
								EditText et = (EditText)findViewById(R.id.EditText01);
								et.setText("您选择了："	+getResources().getStringArray(R.array.msa)[which]);
							}
						});
					dialog = b2.create();			//生成Dialog对象
					break;
				case LIST_DIALOG_SINGLE:
					Builder b3 = new AlertDialog.Builder(this);
					b3.setIcon(R.drawable.icon);//设置对话框的图标
					b3.setTitle(R.string.title3);//设置对话框的标题
					b3.setSingleChoiceItems(//设置单选列表选项
						R.array.msa, 0,	new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								EditText et = (EditText)findViewById(R.id.EditText01);
								et.setText("您选择了："	+ getResources().getStringArray(R.array.msa)[which]);
							}
						});
					b3.setPositiveButton(//在对话框下面添加一个确定按钮
							R.string.ok,//按钮显示的文本
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which){}
							});
					
					b3.setNegativeButton(
							R.string.cancel,//按钮显示的文本
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which){ finish();}
							});

					dialog = b3.create();			//生成Dialog对象
					break;
				default:
					break;
			}
			return dialog;						//返回生成Dialog的对象
		}
	    
	}