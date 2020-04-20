package com.example.homwork02;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.view.View.OnClickListener;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_send;
    private EditText et_name;
    private RadioButton manRadio;
    private RadioButton womanRadio;
    private EditText et_password;
    private EditText et_age;
    private boolean jud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        et_age = (EditText) findViewById(R.id.et_age);
        btn_send = (Button) findViewById(R.id.btn_send);
        manRadio = (RadioButton) findViewById(R.id.radioMale);
        womanRadio = (RadioButton) findViewById(R.id.radioFemale);
        //点击发送按钮进行数据传递
        btn_send.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                jud = judage();
                if(jud){
                    passDate();
                }else {
                    Toast.makeText(RegisterActivity.this, "请检查您的年龄输入", Toast.LENGTH_LONG).show();
                }

            }
        });
        et_age.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    jud = judage();
                }
            }
        });
    }
    //传递数据
    public void passDate() {
        //创建Intent对象，启动RegisterViewActivity
        Intent intent = new Intent(this, RegisterViewActivity.class);
        //取出数据
        String nameString = et_name.getText().toString().trim();
        String passwordString = et_password.getText().toString().trim();
        int ageInt = Integer.parseInt(et_age.getText().toString().trim());
        String strString = "";
        if(manRadio.isChecked()){
            strString = "男";
        }else if(womanRadio.isChecked()){
            strString = "女";
        }
        //新建bundle
        Bundle bundle = new Bundle();
        //将数据存入bundle对象
        bundle.putInt("age",ageInt);
        bundle.putString("name",nameString);
        bundle.putString("password",passwordString);
        bundle.putString("sex",strString);
        //将bundle存入intent对象
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private boolean judage() {
        if (TextUtils.isEmpty(et_age.getText().toString().trim())) {
            Toast.makeText(RegisterActivity.this, "请输入您的年龄", Toast.LENGTH_LONG).show();
            et_age.requestFocus();
            return false;
        } else {
            String jud_age = et_age.getText().toString().trim();
            //1-150岁
            String num = "^[1-9]\\d?$|^1[0-4]\\d$|^0$|^150$";
            if (jud_age.matches(num))
                return true;
            else {
                Toast.makeText(RegisterActivity.this, "请输入正确的年龄", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }

}
