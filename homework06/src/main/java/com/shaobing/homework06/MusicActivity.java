package com.shaobing.homework06;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity {

    private Button btn_start,btn_pause,btn_stop;
    private MusicService.MyBinder binder;
    private MyConn conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_pause = (Button)findViewById(R.id.btn_pause);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        final Intent intent = new Intent(MusicActivity.this,MusicService.class);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conn == null)
                    conn = new MyConn();
                bindService(intent,conn,BIND_AUTO_CREATE);
                startService(intent);
            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binder.callMethodInService();
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
                if (conn != null) {
                    unbindService(conn);
                    conn = null;
                }
            }
        });
    }

    private class MyConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MusicService.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
