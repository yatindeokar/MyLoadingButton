package com.example.myloadingbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by deokar on 12/11/17.
 */

public class ButtonAnimation {

    String TAG = ButtonAnimation.class.getSimpleName();

    int animationTime;
    int buttonExpandedWidth;

    public ButtonAnimation(int buttonExpandedWidth, int animationTime) {
        this.buttonExpandedWidth = buttonExpandedWidth;
        this.animationTime = animationTime;
    }

    /**
     * Fade in view and visible another view on animation end
     * value range from 0f to 1f , 0 for fade in 1 for fade out
     * @param view
     * @param visibleView
     */
    public void onFadeInVisibleView(View view, final View visibleView) {

        view.animate()
                .alpha(0f)
                .setDuration(animationTime)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        visibleView.setVisibility(View.VISIBLE);
                    }
                })
                .start();

    }

    /**
     * Fade out view and visible another view on animation end.
     * value range from 0f to 1f , 0 for fade in 1 for fade out
     * @param view
     * @param visibleView
     */
    public void onFadeOutVisibleView(View view, final View visibleView) {

        view.animate()
                .alpha(0f)
                .setDuration(animationTime)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        visibleView.setVisibility(View.VISIBLE);
                    }
                })
                .start();

    }

    /**
     * Fade out view.
     * @param view
     */
    public void fadeOutView(View view) {

        view.animate()
                .alpha(1f)
                .setDuration(animationTime)
                .start();

    }

    /**
     * Fade in view.
     * @param view
     */
    public void fadeInView(View view) {

        view.animate()
                .alpha(0f)
                .setDuration(animationTime)
                .start();

    }


    /**
     * Animate view to get shrink effect.
     * @param view
     */
    public void animateShapeShrink(final View view) {

        ValueAnimator anim = ValueAnimator.ofInt(view.getMeasuredWidth(), view.getMeasuredHeight());

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = val;
                view.requestLayout();
            }
        });

        anim.setDuration(animationTime);
        anim.start();

    }

    /**
     * Animate view to get expand effect.
     * @param view
     */
    public void animateShapeExpand(final View view) {

        Log.d(TAG, "animateShapeExpand: "+buttonExpandedWidth);

        ValueAnimator anim = ValueAnimator.ofInt(view.getMeasuredWidth(), buttonExpandedWidth);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = val;
                view.requestLayout();
            }
        });

        anim.setDuration(animationTime);
        anim.start();

    }



}
