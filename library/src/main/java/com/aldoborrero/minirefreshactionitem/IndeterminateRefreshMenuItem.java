package com.aldoborrero.minirefreshactionitem;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class IndeterminateRefreshMenuItem extends AbstractRefreshMenuItem {

    private ImageView mRefreshButton;

    private ProgressBar mProgressIndicator;

    public IndeterminateRefreshMenuItem(Context context) {
        super(context);
    }

    public IndeterminateRefreshMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IndeterminateRefreshMenuItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onInflateLayout(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.mrai__indeterminate_refresh, this, true);
        mRefreshButton = (ImageView) view.findViewById(R.id.refresh_button);
        mRefreshButton.setOnClickListener(this);
        mRefreshButton.setOnLongClickListener(this);
        mProgressIndicator = (ProgressBar) view.findViewById(R.id.indeterminate_progress_indicator);
    }

    @Override
    protected void onObtainStyledAttributes(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MiniRefreshActionItem, defStyle, R.style.Widget_MiniRefreshActionItem_Dark);
        int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.MiniRefreshActionItem_refreshActionItemIcon) {
                Drawable refreshButtonIcon = a.getDrawable(attr);
                mRefreshButton.setImageDrawable(refreshButtonIcon);
            } else if (attr == R.styleable.MiniRefreshActionItem_refreshActionItemBackground) {
                Drawable drawable = a.getDrawable(attr);
                mRefreshButton.setBackgroundDrawable(drawable);
            }
        }
        a.recycle();
    }

    @Override
    protected void onUpdateViewsVisibility() {
        mRefreshButton.setVisibility(mIsInProgress ? View.GONE : View.VISIBLE);
        mProgressIndicator.setVisibility(mIsInProgress ? View.VISIBLE : View.GONE);
    }

}
