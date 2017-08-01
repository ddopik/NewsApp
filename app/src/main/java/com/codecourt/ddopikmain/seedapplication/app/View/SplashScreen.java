package com.codecourt.ddopikmain.seedapplication.app.View;

/**
 * Created by ddopi on 6/15/2017.
 */


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import com.codecourt.ddopikmain.seedapplication.R;

public class SplashScreen extends AppCompatActivity {

    ProgressBar mprogressBar;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screeen);
//        EventBus.getDefault().register(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        splashStart();
    }

    public void splashStart() {
        mprogressBar = (ProgressBar) findViewById(R.id.circular_progress_bar);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0,25);
        anim.setDuration(10000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                SplashScreen.this.startActivity(intent);
            }
        }, 10000);
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void progressState(ProgressState progressState)
//    {
//        Log.e("SplashScreen","progressState---->is"+progressState.isProgressState());
//        if(progressState.isProgressState())
//        {
//            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
//            SplashScreen.this.startActivity(intent);
//        }
//
//    }

    @Override
    protected void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }
}