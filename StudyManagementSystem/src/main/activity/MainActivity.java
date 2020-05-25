package com.example.studymanagementsystem.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.studymanagementsystem.R;

public class MainActivity extends Activity {

    private String userId;
    private Button btn_ac_main_updata_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        userId = intent.getStringExtra("userId");
        initView();
        btn_ac_main_updata_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UpdataPasswordActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
    }
    private void initView(){
        btn_ac_main_updata_password = (Button)findViewById(R.id.btn_ac_main_updata_password);
    }
}
