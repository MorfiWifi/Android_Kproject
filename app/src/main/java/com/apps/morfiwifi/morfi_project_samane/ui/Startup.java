package com.apps.morfiwifi.morfi_project_samane.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.wang.avi.AVLoadingIndicatorView;

public class Startup extends AppCompatActivity {
    private static boolean firstTime  = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        AVLoadingIndicatorView avLoadingIndicatorView  =  findViewById(R.id.avi);
        avLoadingIndicatorView.smoothToShow();
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext() , R.anim.fade_in);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext() , R.anim.fade_in2);
        findViewById(R.id.im_icon).setAnimation(animation);
        findViewById(R.id.tv_welcome).setAnimation(animation2);

        final Startup activity = this;
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                activity.onBackPressed();
            }
        };
        if (firstTime){
            handler.postDelayed(runnable, 2500L);
            firstTime = false;
        }else {
            handler.postDelayed(runnable, 0);
        }

    }
}
