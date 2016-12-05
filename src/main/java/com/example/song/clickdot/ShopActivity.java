package com.example.song.clickdot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

/**
 * Created by Song on 2016-12-05.
 */

public class ShopActivity extends AppCompatActivity{

    public static boolean item = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        final Button btn_Re = (Button)findViewById(R.id.returnBt);
        final ImageView item_1 = (ImageView)findViewById(R.id.item_CS);

        btn_Re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intentMain);
                finish();
            }
        });

        item_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = true;
                Item.item1 = true;
            }
        });

    }
}
