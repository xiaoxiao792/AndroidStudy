package com.example.homework03;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_main = (TextView)findViewById(R.id.tv_main);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");
        tv_main.setText("欢迎您："+username);
    }
}
