package com.example.animationdemo;

import android.view.View;
import android.view.animation.Interpolator;

public class MyAccelerateInterpolator implements Interpolator {

    private final float mFactor;
    private final double mDoubleFactor;
    private View mView = null;

    public MyAccelerateInterpolator() {
        mFactor = 1.0f;
        mDoubleFactor = 2.0;
    }

    public MyAccelerateInterpolator(float factor) {
        mFactor = factor;
        mDoubleFactor = 2 * mFactor;
    }


    @Override
    public float getInterpolation(float v) {
        float y = 0;
        if (mFactor == 1.0f) {
            y = v * v;
        } else {
            y = (float) Math.pow(v, mDoubleFactor);
        }
        mListener.onInterpolationChange(y, v);
        return y;
    }

    public void setTargetView(View target) {
        mView = target;
    }

    public interface onInterpolationChangeListener {
        void onInterpolationChange(float y, float t);
    }

    onInterpolationChangeListener mListener = null;

    public void setOnInterpolationListener(onInterpolationChangeListener listener) {
        mListener = listener;
    }

}
