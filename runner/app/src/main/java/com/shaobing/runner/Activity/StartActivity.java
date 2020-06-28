package com.shaobing.runner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import com.shaobing.runner.Bean.UserBean;
import com.shaobing.runner.DB.MySqlDBAccess;

public class StartActivity extends AppCompatActivity {
    private UserBean user;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!getUserId().equals("")){
            userName = getUserId();
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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(StartActivity.this,MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user",user);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                },1000);
            }
        }else {
            Intent intent = new Intent(StartActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private String getUserId(){
        SharedPreferences sp = getSharedPreferences("runnerConfig",MODE_PRIVATE);
        return sp.getString("userId","");
    }
}
