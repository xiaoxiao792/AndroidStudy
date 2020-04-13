package com.example.helloworld;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SoundplayerActivity extends AppCompatActivity {

    MediaPlayer myplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myplayer = MediaPlayer.create(this,R.raw.a);
        setContentView(R.layout.activity_soundplayer);
        myplayer.start();
    }
}
