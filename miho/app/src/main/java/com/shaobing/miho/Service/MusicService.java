package com.shaobing.miho.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import com.shaobing.miho.R;

/**
 * @className : MusicService
 * @description : 后台音乐
 * @date : 2020/6/3 15:16
 * @author : 邵文炳
 */
public class MusicService extends Service {

    private MediaPlayer mediaPlayer;

    public class MyBinder extends Binder{
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
        mediaPlayer=MediaPlayer.create(this, R.raw.a);
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
