package com.aldoborrero.minirefreshactionitem;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public abstract class AbstractRefreshMenuItem extends FrameLayout implements RefreshableMenuItem,
        View.OnClickListener, View.OnLongClickListener {

    protected boolean mIsInProgress;

    protected MenuItem mMenuItem;

    protected OnRefreshActionListener mRefreshActionListener;

    public AbstractRefreshMenuItem(Context context) {
        this(context, null);
    }

    public AbstractRefreshMenuItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbstractRefreshMenuItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        LayoutInflater inflater = LayoutInflater.from(context);
        onInflateLayout(inflater);

        onObtainStyledAttributes(context, attrs, defStyle);

        onUpdateViewsVisibility();
    }

    protected abstract void onInflateLayout(LayoutInflater inflater);

    protected abstract void onObtainStyledAttributes(Context context, AttributeSet attrs, int defStyle);

    protected abstract void onUpdateViewsVisibility();

    @Override
    public void showProgress(boolean inProgress) {
        if (mIsInProgress == inProgress) {
            return;
        }
        mIsInProgress = inProgress;
        onUpdateViewsVisibility();
    }

    @Override
    public boolean isInProgress() {
        return mIsInProgress;
    }

    @Override
    public void onClick(View v) {
        if (mRefreshActionListener != null) {
            mRefreshActionListener.onRefreshButtonClick(this);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mMenuItem == null || TextUtils.isEmpty(mMenuItem.getTitle())) {
            return true;
        }
        int[] screenPos = new int[2];
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int width = getWidth();
        int height = getHeight();
        int midy = screenPos[1] + height / 2;
        Rect displayFrame = new Rect();

        getLocationOnScreen(screenPos);
        getWindowVisibleDisplayFrame(displayFrame);

        Toast helpText = Toast.makeText(getContext(), mMenuItem.getTitle(), Toast.LENGTH_SHORT);
        if (midy < displayFrame.height()) {
            helpText.setGravity(Gravity.TOP | Gravity.RIGHT, screenWidth - screenPos[0] - width / 2, height);
        } else {
            helpText.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, height);
        }
        helpText.show();
        return true;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.mMenuItem = menuItem;
    }

    public void setOnRefreshActionListener(OnRefreshActionListener listener) {
        this.mRefreshActionListener = listener;
    }

}
