package com.lgshuo.demo;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lgs.pathanim.PathAnimator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean isFirst = false;
    private View mFab;
    private View mToolBar;

    private float mFabHeight, mFabWidth, mFabLeft, mFabRight, mFabTop, mFabBottom;
    private float mToolBarHeight, mToolBarWidth, mToolBarLeft, mToolBarRight, mToolBarTop, mToolBarBottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFab = findViewById(R.id.fab);
        mToolBar = findViewById(R.id.toolBar);
        mFab.setOnClickListener(this);
/*
        PathAnimator mPath = new PathAnimator();
        mPath.moveTo(0,0);
        mPath.arcTo(0,0,300,90,90);
        ObjectAnimator animator = mPath.startAnim(mFab,500);*/
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            isFirst = true;
            mFabHeight = mFab.getHeight();
            mFabWidth = mFab.getWidth();
            mFabLeft = mFab.getLeft();
            mFabRight = mFab.getRight();
            mFabTop = mFab.getTop();
            mFabBottom = mFab.getBottom();

            mToolBarHeight = mToolBar.getHeight();
            mToolBarWidth = mToolBar.getWidth();
            mToolBarLeft = mToolBar.getLeft();
            mToolBarRight = mToolBar.getRight();
            mToolBarTop = mToolBar.getTop();
            mToolBarBottom = mToolBar.getBottom();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                float fabCenterX = (mFabLeft + mFabRight) / 2;
                float fabCenterY = (mFabTop + mFabBottom) / 2;

                float toolBarCenterX = (mToolBarLeft + mToolBarRight) / 2;
                float toolBarCenterY = (mToolBarHeight + mToolBarBottom) / 2;

                PathAnimator pathAnimator = new PathAnimator();

                float v1 = fabCenterY - toolBarCenterY - (fabCenterX - toolBarCenterX)+mToolBarHeight/2;
                pathAnimator.moveTo(0,0);
                pathAnimator.arcTo(0, 0, fabCenterX - toolBarCenterX, -90, -180);
                pathAnimator.lineTo(0, -v1);
//                pathAnimator.moveTo(-fabCenterX + toolBarCenterX,-fabCenterX + toolBarCenterX);
                pathAnimator.startAnim(mFab, 2000);
                break;
        }
    }
}
