package com.example.studymanagementsystem.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.studymanagementsystem.R;
import com.example.studymanagementsystem.bean.UserBean;
import com.example.studymanagementsystem.db.DBAccessUtil;

public class RegisterActivity extends Activity {

    private TextView tv_ac_register_user_id;
    private EditText et_ac_register_user_id,et_ac_register_user_name,et_ac_register_user_card_id;
    private EditText et_ac_register_college,et_ac_register_department,et_ac_register_admission_time;
    private EditText et_ac_register_phone,et_ac_register_email,et_ac_register_major,et_ac_register_grade;
    private EditText et_ac_register_classes,et_ac_register_password,et_ac_register_vpassword;
    private Spinner spn_ac_register_role;
    private RadioButton rb_ac_register_male,rb_ac_register_female;
    private Button btn_ac_register_register;

    private DBAccessUtil dbAccessUtil;
    private String role = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        dbAccessUtil = new DBAccessUtil(this);
        dbAccessUtil.open();
        spn_ac_register_role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    role = "学生";
                    tv_ac_register_user_id.setText(R.string.ac_register_user_id);
                }else {
                    role = "教师";
                    tv_ac_register_user_id.setText(R.string.ac_register_user_id02);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_ac_register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyPassword()){
                    registerUser();
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void initView(){
        tv_ac_register_user_id = (TextView)findViewById(R.id.tv_ac_register_user_id);
        et_ac_register_user_id = (EditText)findViewById(R.id.et_ac_register_user_id);
        et_ac_register_user_name = (EditText)findViewById(R.id.et_ac_register_user_name);
        et_ac_register_user_card_id = (EditText)findViewById(R.id.et_ac_register_user_card_id);
        et_ac_register_college = (EditText)findViewById(R.id.et_ac_register_college);
        et_ac_register_department = (EditText)findViewById(R.id.et_ac_register_department);
        et_ac_register_admission_time = (EditText)findViewById(R.id.et_ac_register_admission_time);
        et_ac_register_phone = (EditText)findViewById(R.id.et_ac_register_phone);
        et_ac_register_email = (EditText)findViewById(R.id.et_ac_register_email);
        et_ac_register_major = (EditText)findViewById(R.id.et_ac_register_major);
        et_ac_register_grade = (EditText)findViewById(R.id.et_ac_register_grade);
        et_ac_register_classes = (EditText)findViewById(R.id.et_ac_register_classes);
        et_ac_register_password = (EditText)findViewById(R.id.et_ac_register_password);
        et_ac_register_vpassword = (EditText)findViewById(R.id.et_ac_register_vpassword);
        spn_ac_register_role = (Spinner)findViewById(R.id.spn_ac_register_role);
        rb_ac_register_male = (RadioButton)findViewById(R.id.rb_ac_register_male);
        rb_ac_register_female = (RadioButton)findViewById(R.id.rb_ac_register_female);
        btn_ac_register_register = (Button)findViewById(R.id.btn_ac_register_register);
    }

    private boolean verifyPassword(){
        boolean key = false;
        if (et_ac_register_password.getText().toString().trim().equals(et_ac_register_vpassword.getText().toString().trim())){
            key = true;
        }
        return key;
    }

    private void registerUser(){
        String sex = "";
        UserBean userBean = new UserBean();
        userBean.setUserid(et_ac_register_user_id.getText().toString().trim());
        userBean.setRole(role);
        userBean.setUsername(et_ac_register_user_name.getText().toString().trim());
        if(rb_ac_register_male.isChecked()){
            sex = "男";
        }else if(rb_ac_register_female.isChecked()){
            sex = "女";
        }
        userBean.setSex(sex);
        userBean.setUsercardid(et_ac_register_user_card_id.getText().toString().trim());
        userBean.setCollege(et_ac_register_college.getText().toString().trim());
        userBean.setDepartment(et_ac_register_department.getText().toString().trim());
        userBean.setAdmissionTime(et_ac_register_admission_time.getText().toString().trim());
        userBean.setPhone(et_ac_register_phone.getText().toString().trim());
        userBean.setEmail(et_ac_register_email.getText().toString().trim());
        userBean.setMajor(et_ac_register_major.getText().toString().trim());
        userBean.setGrade(et_ac_register_grade.getText().toString().trim());
        userBean.setClasses(et_ac_register_classes.getText().toString().trim());
        userBean.setPassword(et_ac_register_password.getText().toString().trim());
        dbAccessUtil.insert(userBean);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAccessUtil.close();
    }
}
