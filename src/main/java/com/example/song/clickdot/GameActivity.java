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
package com.example.song.clickdot;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.media.*;
import android.content.*;

public class GameActivity extends AppCompatActivity {

    //private int count;
    public int dotImageKinds;
    public int imageSwitch;
    private TextView textLove;
    private TextView textMoney;
    private RelativeLayout bg;
   // private RelativeLayout sellBg;
    private static ImageView img;
    private SharedPreferences playerData;
    public SharedPreferences.Editor editor;
    private MediaPlayer backgroundMusic;
    private MediaPlayer dotClickMusic;
    private MediaPlayer dotGrowth;
    private Handler mhandler = new Handler();
    private Handler handler = new Handler();
    private Runnable mMytesk = new Runnable() {
        @Override
        public void run() {
            sellPopUp();
            editor = playerData.edit();
            editor.putInt("DOT_KINDS", 0);
            if(Item.item1 == true) {
                handler.postDelayed(new IncHandler(5, 1000, handler), 100);
            }
            bg.setClickable(true);
        }
    };

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
            //count = playerState.count;
            playerState.count = playerState.count + incAmount;
            textLove.setText(String.valueOf((playerState.count)));
            imageSetting();
            if(isRunning) {
                self.postDelayed(this, time);
            }
        }

        public void stop() { isRunning = false; }
    }


    private  BackPressCloseHandler backPressCloseHandler;
    public PlayerState playerState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        backPressCloseHandler = new BackPressCloseHandler(this);
        playerState = new PlayerState();
        backgroundMusic = MediaPlayer.create(this, R.raw.scene5);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();

        playerData = getSharedPreferences("Data", MODE_PRIVATE);
        bg = (RelativeLayout)findViewById(R.id.main);
        //sellBg = (RelativeLayout)findViewById(R.id.main);
        textLove = (TextView)findViewById(R.id.loveText);
        textMoney = (TextView)findViewById(R.id.moneyText);
        playerState.money = playerData.getInt("MONEY", playerState.money);
        playerState.count = playerData.getInt("LOVE", playerState.count);
        playerState.dotSwitch = playerData.getInt("DOT_KINDS", playerState.dotSwitch);
        //Item.item1 = playerData.getBoolean("ITEM_1", false);
        img = (ImageView) findViewById(R.id.Image_char);
        dotImageKinds = playerState.dotImageKinds;
        imageSwitch = playerState.dotSwitch;
        final ImageView shopBt = (ImageView)findViewById(R.id.goShopButton);
        dotClickMusic = MediaPlayer.create(this, R.raw.cursor2);
        dotGrowth = MediaPlayer.create(this, R.raw.item);


        ShopActivity sh = new ShopActivity();
        //count = playerState.count;
//        boolean item_on_off = Item.item1;

        if(Item.item1 == true) {
            handler.postDelayed(new IncHandler(5, 1000, handler), 100);
        }
        /*if(Item.item2 > 0)
        {
            while(Item.item2 != 0)
            {
                p
            }
        }*/

        imageSetting();
        //int money = playerState.money;
        textMoney.setText(""+playerState.money);
        textLove.setText(""+playerState.count);

        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerState.count++;
                //System.out.println(count);
                textLove.setText(""+playerState.count);
                textMoney.setText(""+playerState.money);
                //System.out.println( playerState.count + "/" + playerState.money + "/" + dotImageKinds + "/" + imageSwitch );
                dotClickMusic.start();
                imageSetting();
            }
        });

        shopBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShop = new Intent(getApplicationContext(), ShopActivity.class);
               // playerState.count = count;
                backgroundMusic.stop();
                playerState.dotImageKinds = dotImageKinds;
                playerState.dotSwitch = imageSwitch;
                startActivity(intentShop);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        textLove = (TextView)findViewById(R.id.loveText);
        textMoney = (TextView)findViewById(R.id.moneyText);
        textMoney.setText(""+playerState.money);
        textLove.setText(""+playerState.count);
        dotImageKinds = playerState.dotImageKinds;
        imageSwitch = playerState.dotSwitch;
        imageSetting();
    }

    @Override
    protected void onStop(){
        super.onStop();
        backgroundMusic.stop();
        playerState.dotSwitch = imageSwitch;
        playerData = getSharedPreferences("Data", MODE_PRIVATE);
        handler.removeMessages(0);
        editor = playerData.edit();
        editor.putInt("MONEY",playerState.money);
        editor.putInt("LOVE", playerState.count);
        editor.putInt("DOT_KINDS", playerState.dotSwitch);
        editor.putBoolean("ITEM_1", Item.item1);
        editor.commit();
    }

    public void imageSetting(){

        PlayerState playerState = new PlayerState();

        int switchN = imageSwitch;
        switch (switchN) {
            case 0:
                img.setImageResource(R.drawable.basic);
                break;
            case 1:
                img.setImageResource(R.drawable.young_dot_2_5);
                break;
            case 2:
                img.setImageResource(R.drawable.young_dot_1_4);
                break;
            case 3:
                img.setImageResource(R.drawable.young_dot_3_sm);
                break;
            default:
                break;
        }

        if(dotImageKinds == 0 && playerState.count >= 500)
        {
            if(imageSwitch  == 1) {
                int n = playerState.FinalDotKinds();
                switch (n) {
                    case 0:
                        img.setImageResource(R.drawable.pet_2);
                        dotImageKinds = 2;
                        playerState.dotImageKinds = dotImageKinds;
                       dotGrowth.start();
                        bg.setClickable(false);
                       mhandler.postDelayed(mMytesk, 1000);
                        break;
                    case 1:
                        img.setImageResource(R.drawable.pet_5);
                        dotImageKinds = 5;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    default:
                        finish();
                        break;

                }
            }else if(imageSwitch  == 2)
            {
                int n = playerState.FinalDotKinds();
                switch (n) {
                    case 0:
                        img.setImageResource(R.drawable.pet_1);
                        dotImageKinds = 1;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    case 1:
                        img.setImageResource(R.drawable.pet_4);
                        dotImageKinds = 4;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    default:
                        finish();
                        break;

                }
            }else if(imageSwitch  == 3)
            {
                int n = playerState.FinalDotKinds();
                switch (n) {
                    case 0:
                        img.setImageResource(R.drawable.pet_3);
                        dotImageKinds = 3;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    case 1:
                        img.setImageResource(R.drawable.sunmoon1212);
                        dotImageKinds = 6;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    default:
                        finish();
                        break;

                }
            }
        }

        if(imageSwitch == 0 && playerState.count >= 10) {
                int n = playerState.DotKinds();
                switch (n) {
                    case 0:
                        img.setImageResource(R.drawable.young_dot_2_5);
                        imageSwitch = 1;
                        break;
                    case 1:
                        img.setImageResource(R.drawable.young_dot_1_4);
                        imageSwitch = 2;
                        break;
                    case 2:
                        img.setImageResource(R.drawable.young_dot_3_sm);
                        imageSwitch = 3;
                        break;
                    default:
                        finish();
                        break;
                }
        }
    }


    public void sellPopUp()
    {
        /*sellBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPopup = new Intent(getApplicationContext(), SellPopupActivity.class);
                startActivity(intentPopup);
            }
        });*/
        Intent intentPopup = new Intent(getApplicationContext(), SellPopupActivity.class);
        handler.removeMessages(0);
        startActivity(intentPopup);
 //       finish();
    }

   /* public void Reset()
    {
        dotImageKinds = playerState.dotSwitch;
        imageSwitch = playerState.dotImageKinds;
        //playerState.dotSwitch = imageSwitch;
        //playerState.dotImageKinds = dotImageKinds;

        //System.out.println( playerState.count + "/" + playerState.money + "/" + dotImageKinds + "/" + imageSwitch );
        imageSetting();
    }*/


    public void onBackPressed() { backPressCloseHandler.onBackPressed(); }
}
