package com.lgs.pathanim;

/**
 * Created by admin on 2017/12/1.
 */

public class PathPoint {
    public static final int MOVE = 0;
    public static final int LINE = 1;
    public static final int CURVE = 2;
    public static final int ARC = 3;
    public int mOperation;
    float mRadius;
    float mAngle,mStartAngle;
    float mX, mY;
    float mControl0X, mControl1X;
    float mControl0Y, mControl1Y;


    private PathPoint(int operation, float c0x, float c0y, float c1x, float c1y, float x, float y) {
        mOperation = operation;
        mX = x;
        mY = y;
        mControl0X = c0x;
        mControl0Y = c0y;
        mControl1X = c1x;
        mControl1Y = c1y;
    }

    private PathPoint(int operation, float x, float y) {
        mOperation = operation;
        mX = x;
        mY = y;
    }

    public PathPoint(int operation, float x, float y, float radius, float angle,float startAngle) {
        mOperation = operation;
        mX = x;
        mY = y;
        this.mRadius = radius;
        this.mAngle = angle;
        this.mStartAngle = startAngle;
    }

    public static PathPoint moveTo(float x, float y) {
        return new PathPoint(MOVE, x, y);
    }

    public static PathPoint lineTo(float x, float y) {
        return new PathPoint(LINE, x, y);
    }

    public static PathPoint curveTo(float c0x, float c0y, float c1x, float c1y, float c2x, float c2y) {
        return new PathPoint(CURVE, c0x, c0y, c1x, c1y, c2x, c2y);
    }

    public static PathPoint arcTo(float x, float y, float radius, float angle,float startAngle) {
        return new PathPoint(ARC, x, y, radius, angle,startAngle);
    }
}
