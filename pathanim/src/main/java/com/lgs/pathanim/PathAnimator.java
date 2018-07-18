package com.lgs.pathanim;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by admin on 2017/12/1.
 */

public class PathAnimator {
    private View view;
    public static final String FABLOCATION = "fabLocation";
    public PathAnimator() {

    }

    ArrayList<PathPoint> mPoints = new ArrayList<>();
    public void moveTo(float x, float y) {
        mPoints.add(PathPoint.moveTo(x,y));
    }

    public void lineTo(float x, float y) {
        mPoints.add(PathPoint.lineTo(x,y));
//        mPoints.add(PathPoint.moveTo(x,y));
    }

    public void curveTo(float c0x, float c0y,float c1x, float c1y,float x, float y) {
        mPoints.add(PathPoint.curveTo(c0x,c0y,c1x,c1y,x,y));
    }
    public  void arcTo(float x, float y, float radius,float angle,float startAngle) {
        mPoints.add(PathPoint.arcTo(x, y, radius, angle,startAngle));
    }

    public Collection<PathPoint> getPoints() {
        return mPoints;
    }
    public void setFabLocation(PathPoint p){
        view.setTranslationX(p.mX);
        view.setTranslationY(p.mY);
    }

    public ObjectAnimator startAnim(View view,long duration){
        this.view = view;
        ObjectAnimator animator = ObjectAnimator.ofObject(this, FABLOCATION, new PathEvaluator(), getPoints().toArray());
        animator.setDuration(duration);

        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
        return animator;
    }
}
