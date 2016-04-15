package com.example.vaibhav.uifactory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibhav.uifactory.R;
import com.example.vaibhav.uifactory.utils.PixelUtils;

public class MatchActivity extends AppCompatActivity {

    private TextView tvStart;

    private ImageView iv1;
    private ImageView iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_match);

        initViews();

        blink();
    }

    private void initViews() {

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);

        tvStart = (TextView) findViewById(R.id.tvStart);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                start();
            }
        });
    }

    private void start() {

        Intent intent = new Intent(this, StartMatchActivity.class);
        Pair pair1 = new Pair(iv1, "iv1");
        Pair pair2 = new Pair(iv2, "iv2");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, pair1, pair2);
        startActivity(intent, options.toBundle());
    }

    private void blink() {

        final Handler handler = new Handler();

        new Thread(new Runnable() {

            @Override
            public void run() {

                int timeToBlink = 160;    //in milissegunds

                try {

                    Thread.sleep(timeToBlink);
                } catch (Exception e) {
                }

                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        if (tvStart.getVisibility() == View.VISIBLE) {
                            tvStart.setVisibility(View.INVISIBLE);
                        } else {
                            tvStart.setTextColor(PixelUtils.getRandomColor());
                            tvStart.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }
}
