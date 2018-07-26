package com.lgshuo.demo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.lgs.pathanim.PathAnimator;

public class SearchDialogFragment extends DialogFragment implements View.OnClickListener {
    private View mFab;
    private View mSearchParent;
    private View mSearchButton;
    private boolean isFirst = true;


    private float mFabHeight, mFabWidth, mFabLeft, mFabRight, mFabTop, mFabBottom;
    private float mSearchParentLeft, mSearchParentRight, mSearchParentTop, mSearchParentBottom;
    private float mSearchParentHeight, mSearchParentWidth;
    private float mSearchHeight, mSearchWidth;

    private View contentView;
    private OnStatusChangedListener listener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.AppDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_search, container, false);
        mFab = contentView.findViewById(R.id.fab);
        mSearchParent = contentView.findViewById(R.id.search_parent);
        mSearchButton = contentView.findViewById(R.id.search_button);


        mSearchParent.setScaleX(0);
        mSearchParent.setScaleY(0);

        mSearchButton.setScaleX(0);
        mSearchButton.setScaleY(0);

        mFab.setOnClickListener(this);
        mSearchButton.setOnClickListener(this);

        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                contentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                calcViewSize();
                mFab.callOnClick();
            }
        });

        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
        return contentView;
    }

    @Override
    public void dismiss() {
        dissmissAnim();
    }


    private void calcViewSize() {
        if (isFirst) {
            isFirst = false;
            mFabHeight = mFab.getHeight();
            mFabWidth = mFab.getWidth();
            mFabLeft = mFab.getLeft();
            mFabRight = mFab.getRight();
            mFabTop = mFab.getTop();
            mFabBottom = mFab.getBottom();
            mSearchParentLeft = mSearchParent.getLeft();
            mSearchParentRight = mSearchParent.getRight();
            mSearchParentTop = mSearchParent.getTop();
            mSearchParentBottom = mSearchParent.getBottom();
            mSearchHeight = mSearchButton.getHeight();
            mSearchWidth = mSearchButton.getWidth();
            mSearchParentHeight = mSearchParent.getHeight();
            mSearchParentWidth = mSearchParent.getWidth();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                showAnim();
                break;
            case R.id.search_button:
                dismiss();
//                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void dissmissAnim() {

        mSearchButton.setScaleX(1);
        mSearchButton.setScaleY(1);

        mSearchParent.animate().scaleX(0).setDuration(1000).start();
        mSearchButton.animate().translationX(0).setDuration(1200).start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFab.setVisibility(View.VISIBLE);
                mSearchParent.setScaleY(0);
                mSearchParent.setScaleX(0);

                mSearchButton.setScaleX(0);
                mSearchButton.setScaleY(0);

                float fabCenterX = (mFabLeft + mFabRight) / 2;
                float fabCenterY = (mFabTop + mFabBottom) / 2;

                float toolBarCenterX = (mSearchParentLeft + mSearchParentRight) / 2;
                float toolBarCenterY = (mSearchParentHeight + mSearchParentBottom) / 2;

                PathAnimator pathAnimator = new PathAnimator();

                float v1 = fabCenterY - toolBarCenterY - (fabCenterX - toolBarCenterX);
                pathAnimator.moveTo(toolBarCenterX-fabCenterX, toolBarCenterY-fabCenterY);
                pathAnimator.lineTo(0, v1);
                pathAnimator.arcTo(0, 0, fabCenterX - toolBarCenterX, 90, 90);

                ObjectAnimator objectAnimator = pathAnimator.startAnim(mFab, 2000);
                objectAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        SearchDialogFragment.super.dismiss();
                        if (listener!=null) {
                            listener.onDismiss();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
//                mFab.setTranslationX(0);
//                mFab.setTranslationY(0);
            }
        }, 1200);
    }


    /**
     * 展示的动画
     */
    private void showAnim() {
        float fabCenterX = (mFabLeft + mFabRight) / 2;
        float fabCenterY = (mFabTop + mFabBottom) / 2;

        float toolBarCenterX = (mSearchParentLeft + mSearchParentRight) / 2;
        float toolBarCenterY = (mSearchParentHeight + mSearchParentBottom) / 2;

        PathAnimator pathAnimator = new PathAnimator();

        float v1 = fabCenterY - toolBarCenterY - (fabCenterX - toolBarCenterX) + mSearchParentHeight / 2;
        pathAnimator.moveTo(0, 0);
        pathAnimator.arcTo(0, 0, fabCenterX - toolBarCenterX, -90, -180);
        pathAnimator.lineTo(0, -v1);
//                pathAnimator.moveTo(-fabCenterX + toolBarCenterX,-fabCenterX + toolBarCenterX);
        ObjectAnimator objectAnimator = pathAnimator.startAnim(mFab, 2000);
        if (listener!=null) {
            listener.onShow();
        }
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mFab.setVisibility(View.GONE);
                mSearchParent.setScaleY(1);
                mSearchParent.setScaleX(mSearchParentHeight / mSearchParentWidth);
                mSearchParent.animate().scaleX(1).setDuration(1000).start();

                mSearchButton.setScaleX(1);
                mSearchButton.setScaleY(1);
                mSearchButton.animate().translationX(mSearchParentWidth / 2 - mSearchWidth / 2).setDuration(1200).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    public void setOnDismissListener(OnStatusChangedListener listener) {
        this.listener = listener;
    }
    public interface OnStatusChangedListener {
        void onDismiss();

        void onShow();
    }

}
