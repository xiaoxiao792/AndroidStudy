package com.example.homework03;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewTextActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("欢迎光临");//设置此Activity的标题
        setContentView(R.layout.activity_view_text);//采用text.xml布局

        Bundle b=getIntent().getExtras();//从本Intent中对应的Buddle中取出数据
        String info=b.getString("name");//得到对应字段的内容


        TextView myview = (TextView) findViewById(R.id.text_view);//text_view在text.xml中设好
        CharSequence old = myview.getText();//得到在text.xml中设定的id为text_view的TextView中的内容
        myview.setText(info + "你好！" + old);//重新设定显示内容
    }
}
