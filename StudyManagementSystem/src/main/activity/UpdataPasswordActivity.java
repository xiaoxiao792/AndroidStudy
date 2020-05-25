package com.example.studymanagementsystem.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.studymanagementsystem.R;
import com.example.studymanagementsystem.db.DBAccessUtil;

public class UpdataPasswordActivity extends Activity {

    private DBAccessUtil dbAccessUtil;
    private EditText et_ac_up_pwd_oldpassword,et_ac_up_pwd_newpassword;
    private Button btn_ac_up_pwd_update_password;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_password);
        initView();
        Intent intent=getIntent();
        userId = intent.getStringExtra("userId");
        dbAccessUtil = new DBAccessUtil(this);
        dbAccessUtil.open();
        btn_ac_up_pwd_update_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verifyOldPassword(userId,et_ac_up_pwd_newpassword.getText().toString().trim(),et_ac_up_pwd_oldpassword.getText().toString().trim())){
                    Toast.makeText(UpdataPasswordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(UpdataPasswordActivity.this, "请检查信息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView(){
        et_ac_up_pwd_newpassword = (EditText)findViewById(R.id.et_ac_up_pwd_newpassword);
        et_ac_up_pwd_oldpassword = (EditText)findViewById(R.id.et_ac_up_pwd_oldpassword);
        btn_ac_up_pwd_update_password = (Button)findViewById(R.id.btn_ac_up_pwd_update_password);
    }

    private boolean verifyOldPassword(String Id, String newPassword, String oldPassword){
        boolean key = false;

        if(dbAccessUtil.update(Id,newPassword,oldPassword)!=0){
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
