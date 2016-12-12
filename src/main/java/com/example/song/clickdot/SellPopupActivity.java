package com.example.song.clickdot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.*;
import android.widget.*;

/**
 * Created by Song on 2016-12-06.
 */

public class SellPopupActivity extends Activity  {

    private int n;
    private GameActivity gameActivity;
  //  private PlayerState pS;
   // private TextView love;
   // private TextView money;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.7f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.activity_popup);

        final Button sellBtn = (Button) findViewById(R.id.sellBtn);
        final TextView sellGuide = (TextView) findViewById(R.id.sellText);
        final RelativeLayout exit = (RelativeLayout)findViewById(R.id.layout_popup);

        gameActivity = new GameActivity();
      // love = (TextView) gameActivity.textLove.findViewById(R.id.loveText);
      //  money = (TextView) gameActivity.textMoney.findViewById(R.id.moneyText);

       // pS = new PlayerState();

        n = gameActivity.playerState.dotImageKinds;

        sellBtn.setClickable(true);
        switch (n) {
            case 1:
                sellGuide.setText("축하드립니다!\n도트가 붕붕이로 자랐습니다!");
                break;
            case 2:
                sellGuide.setText("축하드립니다!\n도트가 멍뭉이로 자랐습니다!");
                break;
            case 3:
                sellGuide.setText("축하드립니다!\n도트가 꿱꿱이로 자랐습니다!");
                break;
            case 4:
                sellGuide.setText("축하드립니다!\n도트가 호산이로 자랐습니다!");
                break;
            case 5:
                sellGuide.setText("축하드립니다!\n도트가 아거로 자랐습니다!");
                break;
            case 6:
                sellGuide.setText("축하드립니다!\n도트가 해달이로 자랐습니다!");
                break;
            default:
                System.out.println("팝업 에러");
                break;

        }


        sellBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              sellBtn.setClickable(false);
              switch (n) {
                  case 1:
                  case 2:
                  case 3:
                  case 4:
                  case 5:
                      gameActivity.playerState.money = gameActivity.playerState.money + 1000;
                      gameActivity.playerState.count = 0;
                      gameActivity.playerState.dotImageKinds = 0;
                      gameActivity.playerState.dotSwitch = 0;
                     // gameActivity.Reset();
                      break;
                  case 6:
                      gameActivity.playerState.money = gameActivity.playerState.money + 2000;
                      gameActivity.playerState.count = 0;
                      gameActivity.playerState.dotImageKinds = 0;
                      gameActivity.playerState.dotSwitch = 0;
                     // gameActivity.Reset();
                       break;
                  default:
                      System.out.println("판매 에러");
                      break;
                }
 //               Intent itMain = new Intent(getApplicationContext(), GameActivity.class);
 //                startActivity(itMain);
                finish();
            }
        });
    }
}
