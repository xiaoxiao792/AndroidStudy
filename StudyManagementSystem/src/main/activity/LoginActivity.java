package com.example.studymanagementsystem.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.studymanagementsystem.R;
import com.example.studymanagementsystem.db.DBAccessUtil;

public class LoginActivity extends Activity {

    private TextView tv_ac_login_register;
    private Button btn_ac_login_login;
    private EditText et_ac_login_user_id;
    private EditText et_ac_login_pwd;
    private DBAccessUtil dbAccessUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        dbAccessUtil = new DBAccessUtil(this);
        dbAccessUtil.open();

        tv_ac_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_ac_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyLogin()){
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("userId", et_ac_login_user_id.getText().toString().trim());
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView(){
        tv_ac_login_register = (TextView)findViewById(R.id.tv_ac_login_register);
        btn_ac_login_login = (Button)findViewById(R.id.btn_ac_login_login);
        et_ac_login_pwd = (EditText)findViewById(R.id.et_ac_login_pwd);
        et_ac_login_user_id = (EditText)findViewById(R.id.et_ac_login_user_id);
    }

    private boolean verifyLogin(){
        boolean key = false;

        String pwd = dbAccessUtil.query(et_ac_login_user_id.getText().toString().trim());

        if (pwd.equals(et_ac_login_pwd.getText().toString().trim())){
            key = true;
        }
        return key;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAccessUtil.close();
    }
}
