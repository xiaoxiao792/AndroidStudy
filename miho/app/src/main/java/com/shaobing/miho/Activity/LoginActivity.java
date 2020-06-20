package com.shaobing.miho.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.shaobing.miho.Bean.UserBean;
import com.shaobing.miho.DB.MySqlDBAccess;
import com.shaobing.miho.R;
import com.shaobing.miho.Tools.Tool;

public class LoginActivity extends AppCompatActivity {

    private UserBean user;
    private EditText et_login_user_name,et_login_password;
    private TextView tv_login_register;
    private Button btn_login_login;
    private String userName,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Tool.steep(this,true,true);
        initView();
        tv_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });
        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = et_login_user_name.getText().toString().trim();
                password = et_login_password.getText().toString().trim();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        user = MySqlDBAccess.queryUser(userName);
                    }
                }).start();
                if (user !=null) {
                    if (user.getPassword().equals(password)) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("user",user);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            }
                        },1000);
                    }
                }
            }
        });
        //监听是否同时有内容
        TextChange textChange = new TextChange();
        et_login_user_name.addTextChangedListener(textChange);
        et_login_password.addTextChangedListener(textChange);
        //监听et_login_user_name是否发生改变，改变清空密码
        et_login_user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                et_login_password.setText(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void initView(){
        et_login_user_name = (EditText)findViewById(R.id.et_login_user_name);
        et_login_password = (EditText)findViewById(R.id.et_login_password);
        tv_login_register = (TextView)findViewById(R.id.tv_login_register);
        btn_login_login = (Button)findViewById(R.id.btn_login_login);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==2){
            if (data!=null) {
                String backUN = data.getStringExtra("userName");
                String backPW = data.getStringExtra("password");
                et_login_user_name.setText(backUN);
                et_login_password.setText(backPW);
            }
        }
    }

    class TextChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean k1 = et_login_user_name.getText().length() > 0;
            boolean k2 = et_login_password.getText().length() > 0;
            if (k1 && k2){
                btn_login_login.setEnabled(true);
                btn_login_login.setTextColor(Color.WHITE);
            }else {
                btn_login_login.setEnabled(false);
                btn_login_login.setTextColor(0xFFB1B1B1);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}