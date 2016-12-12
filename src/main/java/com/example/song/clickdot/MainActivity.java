package com.example.song.clickdot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.media.*;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer backgroundMusic;
    private MediaPlayer startGame;
    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundMusic = MediaPlayer.create(this, R.raw.scene6);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();
        startGame = MediaPlayer.create(this, R.raw.open5);
        backPressCloseHandler = new BackPressCloseHandler(this);

        final ImageButton start = (ImageButton)findViewById(R.id.startButton);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundMusic.stop();
                startGame.start();
                Intent intent_act = new Intent(getApplicationContext(),GameActivity.class);
                startActivity(intent_act);
                finish();
            }
        });

    }

    @Override
    protected void onStop(){
        super.onStop();
        backgroundMusic.stop();
    }

    public void onBackPressed() { backPressCloseHandler.onBackPressed(); }
}
