package com.example.homwork02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterViewActivity extends AppCompatActivity {

    private TextView tv_name;
    private TextView tv_password;
    private TextView tv_sex;
    private TextView tv_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String password = bundle.getString("password");
        String sex = bundle.getString("sex");
        Integer age = bundle.getInt("age");
        tv_name =(TextView) findViewById(R.id.tv_name);
        tv_password = (TextView) findViewById(R.id.tv_password);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_age = (TextView) findViewById(R.id.tv_age);

        tv_name.setText("用户名："+name);
        tv_password.setText("密    码："+password);
        tv_age.setText("年    龄："+age);
        tv_sex.setText("性   别："+sex);
    }

}
