package com.MyDialogDemo;
import android.app.Activity;
import android.app.AlertDialog;//ע����Ҫ����
import android.app.Dialog;//ע����Ҫ����
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;//�б�Ի�������Ҫ����
import android.content.DialogInterface.OnClickListener;//ע����Ҫ����
import android.os.Bundle;//������ʱ��Ҫ�������
import android.view.View;
import android.widget.Button;						
import android.widget.EditText;
public class MyDialogDemo_MainActivity extends Activity {
		final int COMMON_DIALOG = 1;//��ͨ�Ի���id
		final int LIST_DIALOG = 2;//�б�Ի���id
		final int LIST_DIALOG_SINGLE = 3;	//��ѡ�Ի���id
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);//����Ĭ�ϲ��֣����ж�������Ӧ�İ�ť
	        Button btn1 = (Button)findViewById(R.id.Button01);//���Button1����
	        Button btn2 = (Button)findViewById(R.id.Button02);//���Button2����
	        Button btn3 = (Button)findViewById(R.id.Button03);//���Button3����
	        btn1.setOnClickListener(new View.OnClickListener(){ //ΪButton1����OnClickListener������
				public void onClick(View v) {
					showDialog(COMMON_DIALOG);//��ʾCOMMON_DIALOG�Ի���
				}
			});
	        btn2.setOnClickListener(new View.OnClickListener(){//ΪButton2����OnClickListener������
				public void onClick(View v) {
					showDialog(LIST_DIALOG);//��ʾLIST_DIALOG�Ի���
				}
			});
	        btn3.setOnClickListener(new View.OnClickListener(){//ΪButton3����OnClickListener������
				public void onClick(View v) {
					showDialog(LIST_DIALOG_SINGLE);	//��ʾLIST_DIALOG_SINGLE�Ի���
				}
			});
	    }
		@Override
		protected Dialog onCreateDialog(int id) {//��дonCreateDialog����
			Dialog dialog = null;//����һ��Dialog�������ڷ�����Ӧ�ĶԻ�������
			switch(id){		//��id�����ж�
				case COMMON_DIALOG://��ͨ�Ի���
					Builder b1 = new AlertDialog.Builder(this);
					b1.setIcon(R.drawable.icon);		//���öԻ����ͼ��
					b1.setTitle(R.string.title1);			//���öԻ���ı���
					b1.setMessage("��ͨ�Ի���");	//���öԻ������ʾ����
					b1.setPositiveButton(//��Ӱ�ť
							R.string.ok, 
							new OnClickListener() {			//Ϊ��ť��Ӽ�����
								public void onClick(DialogInterface dialog, int which) {
									EditText et = (EditText)findViewById(R.id.EditText01);
									et.setText("��ѡ������ͨ�Ի���"); //����EditText����
								}
							});
					dialog = b1.create();			//����Dialog����
					break;
				case LIST_DIALOG:
					Builder b2 = new AlertDialog.Builder(this);
					b2.setIcon(R.drawable.icon);//���öԻ����ͼ��
					b2.setTitle(R.string.title2);//���öԻ���ı���
					b2.setItems(//�����б��еĸ������ԣ����ַ������飬������ѡ���б�
						R.array.msa, new DialogInterface.OnClickListener() {	//Ϊ�б�����OnClickListener������
							public void onClick(DialogInterface dialog, int which) {
								EditText et = (EditText)findViewById(R.id.EditText01);
								et.setText("��ѡ���ˣ�"	+getResources().getStringArray(R.array.msa)[which]);
							}
						});
					dialog = b2.create();			//����Dialog����
					break;
				case LIST_DIALOG_SINGLE:
					Builder b3 = new AlertDialog.Builder(this);
					b3.setIcon(R.drawable.icon);//���öԻ����ͼ��
					b3.setTitle(R.string.title3);//���öԻ���ı���
					b3.setSingleChoiceItems(//���õ�ѡ�б�ѡ��
						R.array.msa, 0,	new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								EditText et = (EditText)findViewById(R.id.EditText01);
								et.setText("��ѡ���ˣ�"	+ getResources().getStringArray(R.array.msa)[which]);
							}
						});
					b3.setPositiveButton(//�ڶԻ����������һ��ȷ����ť
							R.string.ok,//��ť��ʾ���ı�
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which){}
							});
					
					b3.setNegativeButton(
							R.string.cancel,//��ť��ʾ���ı�
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which){ finish();}
							});

					dialog = b3.create();			//����Dialog����
					break;
				default:
					break;
			}
			return dialog;						//��������Dialog�Ķ���
		}
	    
	}