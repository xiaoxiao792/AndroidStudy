package com.example.homework03;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TextViewDemoActivity extends AppCompatActivity {
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_demo);
        TextView msgTextView = (TextView)findViewById(R.id.mytextview);//得到在main.xml中设定的TextView
        nameEditText = (EditText)findViewById(R.id.editText1);//得到在main.xml中设定的TextView
        // msgTextView.setText(R.string.hello);//为textview指定显示内容（字符串在string.xml中定义）
        Button mybutton = (Button) findViewById(R.id.mybutton);//得到在main.xml中设定的button
        mybutton.setOnClickListener(ifclick);//侦听该按钮是否被点击
    }
    private Button.OnClickListener ifclick = new Button.OnClickListener() {//侦听按钮是否被单击
        public void onClick(View v) {
            String userinputinfo = nameEditText.getText().toString();//得到用户输入的内容
            Bundle bundle = new Bundle();//新建Buddle
            //保存输入的信息
            bundle.putString("name", userinputinfo);//将用户输入的内容存放在name字段中
            Intent myintent=new Intent();
            myintent.setAction("com.example.homework02.intent");
            myintent.putExtras(bundle);//取出buddle信息
            finish();//关闭此Activity
            startActivity(myintent);//确定新的Activity
        }
    };
}
