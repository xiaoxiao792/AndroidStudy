package com.example.homework03;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckBoxDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box_demo);
        Button mybutton = (Button) findViewById(R.id.button_in_mainactivity);//在main.xml中已经定义好
        mybutton.setOnClickListener(ifclick);//侦听按钮是否被点击
    }

    private Button.OnClickListener ifclick = new Button.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(CheckBoxDemoActivity.this, MyOwnCheckBoxActivity.class);
            startActivity(intent);
        }
    };
}
