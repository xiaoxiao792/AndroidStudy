package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_send;
    private EditText et_name;
    private RadioButton manRadio;
    private RadioButton womanRadio;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_send = (Button) findViewById(R.id.btn_send);
        manRadio = (RadioButton) findViewById(R.id.radioMale);
        womanRadio = (RadioButton) findViewById(R.id.radioFemale);
        //点击发送按钮进行数据传递
        btn_send.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                passDate();
            }
        });
    }
    //传递数据
    public void passDate() {
        //创建Intent对象，启动RegisterViewActivity
        Intent intent = new Intent(this, RegisterViewActivity.class);
        //将数据存入Intent对象
        intent.putExtra("name", et_name.getText().toString().trim());
        intent.putExtra("password", et_password.getText().toString().trim());
        String str = "";
        if(manRadio.isChecked()){
            str = "男";
        }else if(womanRadio.isChecked()){
            str = "女";
        }
        intent.putExtra("sex", str);
        startActivity(intent);
    }
}
