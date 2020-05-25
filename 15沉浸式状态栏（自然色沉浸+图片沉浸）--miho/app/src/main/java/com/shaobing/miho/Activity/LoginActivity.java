package com.shaobing.miho.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.shaobing.miho.R;
import com.shaobing.miho.Tools.Tool;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Tool.steep(this,true,true);
    }


}