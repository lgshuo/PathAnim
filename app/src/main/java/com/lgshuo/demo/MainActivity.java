package com.lgshuo.demo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lgs.pathanim.PathAnimator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean isFirst = false;
    private View mFab;
    private View mToolBar;

    private float mFabHeight, mFabWidth, mFabLeft, mFabRight, mFabTop, mFabBottom;
    private float mToolBarHeight, mToolBarWidth, mToolBarLeft, mToolBarRight, mToolBarTop, mToolBarBottom;
    private View mSearch;
    private View mSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFab = findViewById(R.id.fab);
        mToolBar = findViewById(R.id.toolBar);
        mSearch = findViewById(R.id.search);
        mSearchView = findViewById(R.id.iv_bottom_search);
        mSearch.setScaleX(0);
        mSearch.setScaleY(0);

        mSearchView.setScaleX(0);
        mSearchView.setScaleY(0);

        mFab.setOnClickListener(this);
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "搜索", Toast.LENGTH_SHORT).show();
            }
        });
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
                ObjectAnimator objectAnimator = pathAnimator.startAnim(mFab, 2000);
                objectAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mFab.setVisibility(View.GONE);
                        mSearch.setScaleY(1);
                        mSearch.setScaleX(mSearch.getHeight()/mSearch.getWidth());
                        mSearch.animate().scaleX(1).setDuration(1000).start();

                        mSearchView.setScaleX(1);
                        mSearchView.setScaleY(1);
                        mSearchView.animate().translationX(mSearch.getWidth() / 2 - mSearchView.getWidth()/2).setDuration(1200).start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                break;
        }
    }
}
