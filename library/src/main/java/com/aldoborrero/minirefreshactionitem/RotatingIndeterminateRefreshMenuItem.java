package com.aldoborrero.minirefreshactionitem;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class RotatingIndeterminateRefreshMenuItem extends AbstractRefreshMenuItem {

    private ImageView mRefreshButton;

    private AnimatorSet mRefreshAnimatorSet;

    public RotatingIndeterminateRefreshMenuItem(Context context) {
        super(context);
    }

    public RotatingIndeterminateRefreshMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RotatingIndeterminateRefreshMenuItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onInflateLayout(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.mrai__animated_indeterminate_refresh, this, true);
        mRefreshButton = (ImageView) view.findViewById(R.id.refresh_button);
        mRefreshButton.setOnClickListener(this);
        mRefreshButton.setOnLongClickListener(this);
        mRefreshAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.rotating_animation);
        mRefreshAnimatorSet.setTarget(mRefreshButton);
    }

    @Override
    protected void onObtainStyledAttributes(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MiniRefreshActionItem, defStyle, R.style.Widget_MiniRefreshActionItem_Dark);
        int indexCount = a.getIndexCount();
        for (int i = 0; i < indexCount; ++i) {
            int attr = a.getIndex(i);
            if (R.styleable.MiniRefreshActionItem_refreshActionItemIcon == attr) {
                Drawable refreshButtonIcon = a.getDrawable(attr);
                mRefreshButton.setImageDrawable(refreshButtonIcon);
            } else if (R.styleable.MiniRefreshActionItem_refreshActionItemBackground == attr) {
                Drawable drawable = a.getDrawable(attr);
                mRefreshButton.setBackgroundDrawable(drawable);
            }
        }
        a.recycle();
    }

    @Override
    protected void onUpdateViewsVisibility() {
        if (mIsInProgress) {
            mRefreshAnimatorSet.start();
        } else {
            mRefreshAnimatorSet.cancel();
        }
        mRefreshButton.setClickable(!mIsInProgress);
        mRefreshButton.setFocusable(!mIsInProgress);
        mRefreshButton.setEnabled(!mIsInProgress);
    }

}
