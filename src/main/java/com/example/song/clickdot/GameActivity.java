package com.example.song.clickdot;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private static int count = 0;
    private  TextView textView;
    private class IncHandler implements Runnable {
        private int incAmount;
        private long time;
        private boolean isRunning = true;
        private Handler self;
        public IncHandler(int incAmount, long time, Handler self) {
                this.incAmount = incAmount;
                this.time = time;
                this.self = self;
            }
        public void run() {
            count = count + incAmount;
            textView.setText(String.valueOf((count)));
            if(isRunning) {
                self.postDelayed(this, time);
            }
        }

        public void stop() { isRunning = false; }
    }


    private  BackPressCloseHandler backPressCloseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        backPressCloseHandler = new BackPressCloseHandler(this);

        final RelativeLayout bg = (RelativeLayout)findViewById(R.id.main);
        textView = (TextView)findViewById(R.id.textView);
        final ImageView img = (ImageView) findViewById(R.id.Image_char);
        final ImageView shopBt = (ImageView)findViewById(R.id.goShopButton);

        ShopActivity sh = new ShopActivity();

        boolean item_on_off = Item.item1;

        if(item_on_off == true) {
            Handler handler = new Handler();
            handler.postDelayed(new IncHandler(10, 1000, handler), 100);
        }
        if(count >= 10)
        {
            img.setImageResource(R.drawable.young_dot_orange);
        }

        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                //System.out.println(count);
                textView.setText(""+count);

                if(count >= 10)
                {
                    img.setImageResource(R.drawable.young_dot_orange);
                }
            }
        });

        shopBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShop = new Intent(getApplicationContext(), ShopActivity.class);
                startActivity(intentShop);
                finish();
            }
        });

    }

    public void onBackPressed() { backPressCloseHandler.onBackPressed(); }
}
