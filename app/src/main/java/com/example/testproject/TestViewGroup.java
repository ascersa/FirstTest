package com.example.testproject;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class TestViewGroup extends ViewGroup {

    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY &&
                MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {//wrap_content

            int width = getDefaultSize(0, widthMeasureSpec);

            int totalHeight = getPaddingTop() + getPaddingBottom();
            int widthHalf = (width - getPaddingLeft() - getPaddingRight()) / 2;
            int lineCount = 0;
            int leftViewHeight = 0;

            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);

                measureChild(child, widthMeasureSpec, heightMeasureSpec);

                int childMeasureHeight = child.getMeasuredHeight();
                int childMeasureWidth = child.getMeasuredWidth();

                if (lineCount == 0) {
                    if (childMeasureWidth > widthHalf) {
                        totalHeight += childMeasureHeight;
                    } else {
                        lineCount++;
                        leftViewHeight = childMeasureHeight;
                    }
                } else {
                    if (childMeasureWidth > widthHalf) {
                        totalHeight += leftViewHeight;
                    }
                    lineCount = 0;
                    totalHeight += childMeasureHeight;
                }
            }
            super.onMeasure(widthMeasureSpec, totalHeight | MeasureSpec.EXACTLY);
        } else {//match_parent或固定值
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();

        int widthHalf = (getMeasuredWidth() - paddingLeft - paddingRight) / 2;

        int totalHeight = paddingTop;
        int lineCount = 0;
        int leftViewHeight = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            measureChild(child, getMeasuredWidth(), getMeasuredHeight());

            int childMeasureHeight = child.getMeasuredHeight();
            int childMeasureWidth = child.getMeasuredWidth();

            if (lineCount == 0) {
                child.layout(paddingLeft, totalHeight, childMeasureWidth + paddingLeft, childMeasureHeight + totalHeight);
                if (childMeasureWidth > widthHalf) {
                    totalHeight += childMeasureHeight;
                } else {
                    lineCount++;
                    leftViewHeight = childMeasureHeight;
                }
            } else {
                if (childMeasureWidth <= widthHalf) {
                    child.layout(widthHalf + paddingLeft, totalHeight,
                            childMeasureWidth + widthHalf + paddingLeft, childMeasureHeight + totalHeight);
                } else {
                    child.layout(paddingLeft, totalHeight + leftViewHeight,
                            childMeasureWidth + paddingLeft, childMeasureHeight + totalHeight + leftViewHeight);
                    totalHeight += leftViewHeight;
                }
                lineCount = 0;
                totalHeight += childMeasureHeight;
            }
        }
    }
}
