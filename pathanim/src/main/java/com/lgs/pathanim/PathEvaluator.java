package com.lgs.pathanim;

import android.animation.TypeEvaluator;
import android.util.Log;

/**
 * Created by admin on 2017/12/1.
 */

public class PathEvaluator implements TypeEvaluator<PathPoint> {

    private float startX, startY;
    private int lastOperation;

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
            calcStartXY(startValue, endValue);
            float oneMinusT = 1 - t;
            x = oneMinusT * oneMinusT * oneMinusT * startX +
                    3 * oneMinusT * oneMinusT * t * endValue.mControl0X +
                    3 * oneMinusT * t * t * endValue.mControl1X +
                    t * t * t * endValue.mX;

            y = oneMinusT * oneMinusT * oneMinusT * startY +
                    3 * oneMinusT * oneMinusT * t * endValue.mControl0Y +
                    3 * oneMinusT * t * t * endValue.mControl1Y +
                    t * t * t * endValue.mY;
            Log.e("curve", "x:" + x + ";      y" + y);
        } else if (endValue.mOperation == PathPoint.LINE) { //直线运动的计算方式
            calcStartXY(startValue, endValue);
            x = startX + t * (endValue.mX /*- startValue.mX*/);
            y = startY + t * (endValue.mY /*- startValue.mY*/);

            Log.e("line", "x:" + x + ";      y" + y);
        } else if (endValue.mOperation == PathPoint.MOVE) {
            calcStartXY(startValue, endValue);
            x = endValue.mX;
            y = endValue.mY;

            Log.e("move", "x:" + x + ";      y" + y);
        } else if (endValue.mOperation == PathPoint.ARC) {
            calcStartXY(startValue, endValue);
            x = startX + endValue.mX + (float) (endValue.mX - endValue.mRadius * (Math.sin(Math.toRadians(endValue.mAngle * t + endValue.mStartAngle))) + endValue.mRadius * Math.sin(Math.toRadians(endValue.mStartAngle)));
            y = startY + endValue.mY + (float) ((endValue.mRadius * (1 - Math.cos(Math.toRadians(endValue.mAngle * t + endValue.mStartAngle)) - (1 - Math.cos(Math.toRadians(endValue.mStartAngle))))));
            Log.e("arc", "x:" + x + ";      y" + y);
        }
        return PathPoint.moveTo(x, y);
    }

    /**
     * 计算上一次的终点作为第二个点的起点
     * @param startValue
     * @param endValue
     */

    private void calcStartXY(PathPoint startValue, PathPoint endValue) {
        if (lastOperation != endValue.mOperation) {
            if (lastOperation == PathPoint.MOVE) {
                startX = startValue.mX;
                startY = startValue.mY;
            } else if (lastOperation == PathPoint.LINE) {
                startX = startX+startValue.mX;
                startY = startY+startValue.mY;
            } else if (lastOperation == PathPoint.ARC) {
                startX = startValue.mX + (float) (startValue.mX - startValue.mRadius * (Math.sin(Math.toRadians(startValue.mAngle + startValue.mStartAngle))) + startValue.mRadius * Math.sin(Math.toRadians(startValue.mStartAngle)));
                startY = startValue.mY + (float) ((startValue.mRadius * (1 - Math.cos(Math.toRadians(startValue.mAngle + startValue.mStartAngle)) - (1 - Math.cos(Math.toRadians(startValue.mStartAngle))))));
            } else if (lastOperation == PathPoint.CURVE) {
                startX = startValue.mX;
                startY = startValue.mY;
            }
            lastOperation = endValue.mOperation;
        }
    }
}
