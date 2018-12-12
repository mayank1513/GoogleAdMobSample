package com.mayank.poptheballoon;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.davidgassner.android.balloonpopper.R;

public class Cloud extends ImageView implements Animator.AnimatorListener,
        ValueAnimator.AnimatorUpdateListener {

    public static final String TAG = "Cloud";

    private ValueAnimator mAnimator;
    int mDpWidth;

    public Cloud(Context context) {
        super(context);
    }

    public Cloud(Context context, int rawHeight) {
        super(context);

        this.setImageResource(R.drawable.cloud);

        int rawWidth = 2 * rawHeight;

//      Calc cloud height and width as dp
        int dpHeight = pixelsToDp(rawHeight, context);
        mDpWidth = pixelsToDp(rawWidth, context);
        ViewGroup.LayoutParams params =
                new ViewGroup.LayoutParams(mDpWidth, dpHeight);
        setLayoutParams(params);
    }

    public void addCloud (int screenWidth, int duration) {
        mAnimator = new ValueAnimator();
        mAnimator.setDuration(duration);
        mAnimator.setFloatValues(screenWidth, -mDpWidth);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setTarget(this);
        mAnimator.addListener(this);
        mAnimator.addUpdateListener(this);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        setX((Float) animation.getAnimatedValue());
    }

    @Override
    public void onAnimationStart(Animator animation) {}

    @Override
    public void onAnimationEnd(Animator animation) {}

    @Override
    public void onAnimationCancel(Animator animation) {}

    @Override
    public void onAnimationRepeat(Animator animation) {}

    public static int pixelsToDp(int px, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, context.getResources().getDisplayMetrics());
    }
}
