package com.example.vaibhav.uifactory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vaibhav.uifactory.R;

import ui.ProgressAnimator;

public class MainActivity extends AppCompatActivity {

    private TextView tvStart;

    private ImageView iv1;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initViews();

        blink();
    }

    private void initViews() {

        tvStart = (TextView) findViewById(R.id.tvStart);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                start();
            }
        });

        iv1 = (ImageView) findViewById(R.id.iv1);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        final ProgressAnimator progressAnimator = new ProgressAnimator(progressBar);

        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int progress = progressBar.getProgress();

                if(progress < 100) {

                    progressAnimator.moveProgress(20);

                } else {

                    progressAnimator.moveProgress(-60);
                }

            }
        });
    }

    private void start() {

        /*Intent intent = new Intent(this, StartMatchActivity.class);
        startActivity(intent);*/

        Intent intent = new Intent(getApplicationContext(), StartMatchActivity.class);
        Pair pair1 = new Pair(iv1, "iv1");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, pair1);
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
                            //tvStart.setTextColor(PixelUtils.getRandomColor());
                            tvStart.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }
}
