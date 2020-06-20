package com.shaobing.miho.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.shaobing.miho.Bean.UserBean;
import com.shaobing.miho.DB.MySqlDBAccess;
import com.shaobing.miho.R;
import com.shaobing.miho.Tools.Tool;

public class RegisterActivity extends AppCompatActivity {

    private UserBean user;
    private EditText et_register_user_name,et_register_password;
    private Button btn_register_register;
    private String userName,password;
    private int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Tool.steep(this,true,true);
        initView();
        btn_register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = et_register_user_name.getText().toString().trim();
                password = et_register_password.getText().toString().trim();
                user = new UserBean();
                user.setUserId(userName);
                user.setPassword(password);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        key = MySqlDBAccess.insert(user);
                    }
                }).start();
                if (key!=0) {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent();
                            intent.putExtra("userName",userName);
                            intent.putExtra("password",password);
                            setResult(2,intent);
                            finish();
                        }
                    },1000);
                }
            }
        });
        //监听是否同时有内容
        TextChange textChange = new TextChange();
        et_register_user_name.addTextChangedListener(textChange);
        et_register_password.addTextChangedListener(textChange);
        //监听et_login_user_name是否发生改变，改变清空密码
        et_register_user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                et_register_password.setText(null);
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
        et_register_user_name = (EditText)findViewById(R.id.et_register_user_name);
        et_register_password = (EditText)findViewById(R.id.et_register_password);
        btn_register_register = (Button)findViewById(R.id.btn_register_register);
    }

    class TextChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean k1 = et_register_user_name.getText().length() > 0;
            boolean k2 = et_register_password.getText().length() > 0;
            if (k1 && k2){
                btn_register_register.setEnabled(true);
                btn_register_register.setTextColor(Color.WHITE);
            }else {
                btn_register_register.setEnabled(false);
                btn_register_register.setTextColor(0xFFB1B1B1);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
