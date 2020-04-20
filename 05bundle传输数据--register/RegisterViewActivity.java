package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterViewActivity extends AppCompatActivity {

    private TextView tv_name;
    private TextView tv_password;
    private TextView tv_sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        Intent intent=getIntent();
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");
        String sex = intent.getStringExtra("sex");
        tv_name =(TextView) findViewById(R.id.tv_name);
        tv_password = (TextView) findViewById(R.id.tv_password);
        tv_sex = (TextView) findViewById(R.id.tv_sex);

        tv_name.setText("用户名："+name);
        tv_password.setText("密    码："+password);
        tv_sex.setText("性   别："+sex);
    }

}
