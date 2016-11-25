package com.example.song.clickdot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final TextView txCount = (TextView)findViewById(R.id.textView);
        final ImageView img = (ImageView) findViewById(R.id.Image_char);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                //System.out.println(count);
                txCount.setText(""+count);

                if(count >= 10)
                {
                    img.setImageResource(R.drawable.young_dot_orange);
                }
            }
        });

    }
}
