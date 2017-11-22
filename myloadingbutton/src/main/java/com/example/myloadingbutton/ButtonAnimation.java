package com.example.myloadingbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

//MIT License
//
//        Copyright (c) 2017 Yatin I Deokar.
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
     * @param view
     */
    public void animateShapeExpand(final View view) {

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
