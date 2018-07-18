package com.lgs.pathanim;

import android.animation.TypeEvaluator;
import android.util.Log;

/**
 * Created by admin on 2017/12/1.
 */

public class PathEvaluator implements TypeEvaluator<PathPoint> {

    /**
     * @param t
     * @param startValue
     * @param endValue
     * @return
     */
    @Override
    public PathPoint evaluate(float t, PathPoint startValue, PathPoint endValue) {
        float x = 0, y = 0;
        if (endValue.mOperation == PathPoint.CURVE) {
            float oneMinusT = 1 - t;
            x = oneMinusT * oneMinusT * oneMinusT * startValue.mX +
                    3 * oneMinusT * oneMinusT * t * endValue.mControl0X +
                    3 * oneMinusT * t * t * endValue.mControl1X +
                    t * t * t * endValue.mX;

            y = oneMinusT * oneMinusT * oneMinusT * startValue.mY +
                    3 * oneMinusT * oneMinusT * t * endValue.mControl0Y +
                    3 * oneMinusT * t * t * endValue.mControl1Y +
                    t * t * t * endValue.mY;

        } else if (endValue.mOperation == PathPoint.LINE) { //直线运动的计算方式
            x = startValue.mX + t * (endValue.mX /*- startValue.mX*/);
            y = startValue.mY + t * (endValue.mY /*- startValue.mY*/);

            Log.e("line", "x: "+x +";      y: "+y+"--------------------------t :"+t);
        } else if (endValue.mOperation == PathPoint.MOVE) {
            x = endValue.mX;
            y = endValue.mY;
        } else if (endValue.mOperation == PathPoint.ARC) {
            x = endValue.mX + (float) (endValue.mX - endValue.mRadius * (Math.sin(Math.toRadians(endValue.mAngle * t+endValue.mStartAngle)))+endValue.mRadius*Math.sin(Math.toRadians(t+endValue.mStartAngle)));
            y = endValue.mY + (float) ((endValue.mRadius * (1 - Math.cos(Math.toRadians(endValue.mAngle * t + endValue.mStartAngle)) - (1 - Math.cos(Math.toRadians(endValue.mStartAngle))))));
            Log.e("ARC", "x: "+x +";      y: "+y);
        }

        return PathPoint.moveTo(x, y);
    }
}
