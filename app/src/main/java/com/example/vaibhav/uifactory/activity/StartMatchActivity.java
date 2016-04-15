package com.example.vaibhav.uifactory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.vaibhav.uifactory.R;

import ui.animation.CircularPropertyAnimation;
import ui.animation.CircularViewAnimation;

public class StartMatchActivity extends AppCompatActivity {

    private RelativeLayout rlAnimate;

    private ImageView ivCircle;

    private ImageView iv1;

    private ImageView iv2;

    private ImageView ivCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_match);

        initViews();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animate();
            }
        }, 100);
        //animate();
    }

    private void animate() {

        final CircularPropertyAnimation anim2 = new CircularPropertyAnimation(iv2, ivCircle, 180);
        anim2.setInterpolator(new LinearInterpolator());
        anim2.setDuration(11000);
        //anim2.setRepeatCount(100);
        iv2.startAnimation(anim2);


        final CircularPropertyAnimation anim = new CircularPropertyAnimation(iv1, ivCircle, 0);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(11000);
        //anim.setRepeatCount(100);
        iv1.startAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intent = new Intent(getApplicationContext(), MatchActivity.class);
                Pair pair1 = new Pair(iv1, "iv1");
                Pair pair2 = new Pair(iv2, "iv2");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(StartMatchActivity.this, pair1, pair2);
                startActivity(intent, options.toBundle());

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void animate2() {

        final CircularViewAnimation anim2 = new CircularViewAnimation(iv2, ivCircle, 180);
        anim2.setInterpolator(new LinearInterpolator());
        anim2.setDuration(11000);
        //anim2.setRepeatCount(100);
        iv2.startAnimation(anim2);


        final CircularViewAnimation anim = new CircularViewAnimation(iv1, ivCircle, 0);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(11000);
        //anim.setRepeatCount(100);
        iv1.startAnimation(anim);

        anim.setMyAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intent = new Intent(getApplicationContext(), MatchActivity.class);
                Pair pair1 = new Pair(iv1, "iv1");
                Pair pair2 = new Pair(iv2, "iv2");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(StartMatchActivity.this, pair1, pair2);
                startActivity(intent, options.toBundle());

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void animate3() {

        /*RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(3000);
        rotate.setRepeatCount(5);
        rotate.setInterpolator(new LinearInterpolator());

        rlAnimate.startAnimation(rotate);*/

    }

    private void initViews() {

        rlAnimate = (RelativeLayout) findViewById(R.id.rlAnimate);
        rlAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animate();
            }
        });

        ivCircle = (ImageView) findViewById(R.id.ivCircle);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        ivCenter = (ImageView) findViewById(R.id.ivCenter);

        iv1.setVisibility(View.VISIBLE);
        iv2.setVisibility(View.VISIBLE);

    }

}
