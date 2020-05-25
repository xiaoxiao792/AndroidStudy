package com.shaobing.homework06;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

public class MusicService extends Service {

    private MediaPlayer mediaPlayer;

    class MyBinder extends Binder{
        public void callMethodInService(){
            Log.i("MyBinder", "callMethodInService");
            methodInService();
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("MusicService", "onBind");
        return new MyBinder();
    }

     

    @Override
    public void onCreate() {
        Log.i("MusicService", "onCreate: ");
        super.onCreate();
        mediaPlayer=MediaPlayer.create(this,R.raw.a);
    }

    @Override
    public void onDestroy() {
        Log.i("MusicService", "onDestroy: ");
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MusicService", "onStartCommand: ");
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }
    
    public void methodInService(){
        Log.i("MusicService", "methodInService");
        mediaPlayer.pause();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("MusicService", "onUnbind");
        return super.onUnbind(intent);
    }
}
