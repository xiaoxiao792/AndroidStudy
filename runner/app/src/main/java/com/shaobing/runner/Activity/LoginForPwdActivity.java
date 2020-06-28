package com.shaobing.runner.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.shaobing.runner.Bean.UserBean;
import com.shaobing.runner.DB.MySqlDBAccess;
import com.shaobing.runner.R;
import com.shaobing.runner.Tools.Tool;

public class LoginForPwdActivity extends AppCompatActivity {

    private UserBean user;
    private String userName,password;
    private EditText et_login_pwd_phone,et_login_pwd_pwd;
    private TextView tv_login_login03,tv_login_pwd_login,tv_login_register02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_for_pwd);
        Tool.steep(this,true,true);
        initView();

        tv_login_login03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginForPwdActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tv_login_pwd_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = et_login_pwd_phone.getText().toString().trim();
                password = et_login_pwd_pwd.getText().toString().trim();
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        user = MySqlDBAccess.queryUser(userName);
                    }
                });
                t1.start();
                try {
                    t1.join(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (user !=null) {
                    if (user.getUserPassword().equals(password)) {
                        //写入配置文件
                        putUserId(userName);
                        Toast.makeText(LoginForPwdActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(LoginForPwdActivity.this,MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("user",user);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            }
                        },1000);
                    }else {
                        Toast.makeText(LoginForPwdActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginForPwdActivity.this, "手机号未注册", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_login_register02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginForPwdActivity.this,RegisterActivity.class);
                startActivityForResult(intent,11);
            }
        });

        //监听是否同时有内容
        TextChange textChange = new TextChange();
        et_login_pwd_phone.addTextChangedListener(textChange);
        et_login_pwd_pwd.addTextChangedListener(textChange);
        //监听et_login_user_name是否发生改变，改变清空密码
        et_login_pwd_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                et_login_pwd_pwd.setText(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initView() {
        et_login_pwd_phone = (EditText)findViewById(R.id.et_login_pwd_phone);
        et_login_pwd_pwd = (EditText)findViewById(R.id.et_login_pwd_pwd);
        tv_login_login03 = (TextView)findViewById(R.id.tv_login_login03);
        tv_login_pwd_login = (TextView)findViewById(R.id.tv_login_pwd_login);
        tv_login_register02 = (TextView)findViewById(R.id.tv_login_register02);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==11&&resultCode==2){
            if (data!=null) {
                String backUN = data.getStringExtra("userName");
                String backPW = data.getStringExtra("password");
                et_login_pwd_phone.setText(backUN);
                et_login_pwd_pwd.setText(backPW);
            }
        }
    }

    class TextChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean k1 = et_login_pwd_phone.getText().length() > 0;
            boolean k2 = et_login_pwd_pwd.getText().length() > 0;
            if (k1 && k2){
                tv_login_pwd_login.setEnabled(true);
            }else {
                tv_login_pwd_login.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private void putUserId(String userId){
        SharedPreferences sp = getSharedPreferences("runnerConfig",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userId",userId);
        editor.apply();
    }
}
