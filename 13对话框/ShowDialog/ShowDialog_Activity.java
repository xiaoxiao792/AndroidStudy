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
        setContentView(R.layout.main);//Ӧ��Ĭ�ϵĲ���main.xml
        Button mybutton = (Button)findViewById(R.id.button1);//��ʾidΪ��button1���İ�ť���䶨���main.xml
        //String pw = findViewById(R.id.password_edit).toString();//����
        mybutton.setOnClickListener(new OnClickListener(){ //���������˰�ť���¼�
          public void onClick(View v) {//�����˰�ť����ʾ�Ի��򡣶Ի���Ĳ��ֶ�����res\dialogshow.xml��
           	 LayoutInflater usingdialoglayoutxml = LayoutInflater.from(ShowDialog_Activity.this);//����Ĳ���ShowDialog_ActivityΪ����
           	 final View myviewondialog = usingdialoglayoutxml.inflate(R.layout.dialogshow, null);//�����趨�Ĳ�����ʾ�Ի���
                	AlertDialog mydialoginstance = new AlertDialog.Builder(ShowDialog_Activity.this) //����Ĳ���ShowDialog_ActivityΪ����
                		.setIcon(R.drawable.icon)//ͼ�꣬��ʾ�ڶԻ���������
                		.setTitle("�û���¼����")  //�Ի������
                		.setView(myviewondialog)//ע�����Ϊ���涨���Viewʵ��������Ϊ��ʾR.layout.dialogshow.xml��������ļ�
                		.setPositiveButton("��¼", new DialogInterface.OnClickListener() { //��ȷ������ť��Ӧ�Ĺ���
                			public void onClick(DialogInterface dialog, int whichButton) {//�����Ƿ��е������ȷ����ť
                				Toast.makeText(getApplicationContext(), "��л����������Ϣ���ټ�", Toast.LENGTH_LONG).show();
                			}})
                		.setNegativeButton("�˳�", new DialogInterface.OnClickListener() { //����������ť��Ӧ�Ĺ���
                			public void onClick(DialogInterface dialog, int whichButton) {  //�����Ƿ��е������ȡ����ť
                				ShowDialog_Activity.this.finish();//�˳�����
                			}})
                		.create(); 
                	mydialoginstance.show(); //��ʾ�Ի���
                }
        });
    }
}