package com.example.testproject;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import androidx.interpolator.view.animation.FastOutLinearInInterpolator;

public class AnimUtil {

    public static void expand(final View view) {

        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final int viewHeight = view.getMeasuredHeight();

        view.getLayoutParams().height = 0;

        view.setVisibility(View.VISIBLE);

        Animation animation = new Animation() {

            @Override

            protected void applyTransformation(float interpolatedTime, Transformation t) {

                if (interpolatedTime == 1) {

                    view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

                } else {

                    view.getLayoutParams().height = (int) (viewHeight * interpolatedTime);

                }

                view.requestLayout();

            }

        };

        animation.setDuration((int) (viewHeight / view.getContext().getResources().getDisplayMetrics().density));

        animation.setInterpolator(new FastOutLinearInInterpolator());

        view.startAnimation(animation);

    }

    public static void collapse(final View view) {

//        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final int viewHeight = view.getMeasuredHeight();

        Animation animation = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                Log.d("xjf", "applyTransformation: " + interpolatedTime);
                if (interpolatedTime == 1) {

                    view.setVisibility(View.GONE);

                } else {

                    view.getLayoutParams().height = viewHeight - (int) (viewHeight * interpolatedTime);

                    view.requestLayout();

                }

            }

        };

        animation.setDuration((int) (viewHeight / view.getContext().getResources().getDisplayMetrics().density));

        animation.setInterpolator(new LinearInterpolator());

        view.startAnimation(animation);

    }

}
