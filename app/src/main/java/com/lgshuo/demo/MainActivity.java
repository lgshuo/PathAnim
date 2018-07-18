package com.lgshuo.demo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lgs.pathanim.AnimatorPath;

public class MainActivity extends AppCompatActivity {

    private View mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFab = findViewById(R.id.fab);
        AnimatorPath mPath = new AnimatorPath();
        mPath.moveTo(0, 0);
        mPath.arcTo(0,0,100,180,90);
        ObjectAnimator animator = mPath.startAnim(mFab);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mFab.animate().scaleXBy(13)
                        .scaleYBy(13)
                        .setDuration(1000);
            }
        });
    }
}
