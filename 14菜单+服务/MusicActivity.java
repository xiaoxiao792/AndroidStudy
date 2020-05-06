package com.example.helloworld;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity {

    private Button btn_music;
    private MusicService.MyBinder binder;
    private MyConn conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        btn_music = (Button)findViewById(R.id.btn_music);
        btn_music.setOnCreateContextMenuListener(this);
        btn_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(btn_music);
                btn_music.showContextMenu();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,1,0,"播放");
        menu.add(0,2,0,"暂停");
        menu.add(0,3,0,"停止");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent intent = new Intent(MusicActivity.this,MusicService.class);
        switch (item.getItemId()){
            case 1:
                if (conn == null)
                    conn = new MyConn();
                btn_music.setText("播放");
                bindService(intent,conn,BIND_AUTO_CREATE);
                startService(intent);
                break;
            case 2:
                binder.callMethodInService();
                btn_music.setText("暂停");
                break;
            case 3:
                btn_music.setText("停止");
                stopService(intent);
                if (conn != null) {
                    unbindService(conn);
                    conn = null;
                }
                break;
            default:
                break;
        }
        return true;
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
