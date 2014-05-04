package com.aldoborrero.minirefreshactionitem;

/**
 * Defines an extension for {@link android.view.MenuItem} that make them refresheable.
 */
public interface RefreshableMenuItem {

    /**
     * Change the checked state of this refresheable menu item
     *
     * @param inProgress The new progress state
     */
    void showProgress(boolean inProgress);

    /**
     * @return The current state of this refresheable menu item
     */
    boolean isInProgress();

    /**
     * Interface definition for a callback to be invoked when a
     * {@link com.aldoborrero.minirefreshactionitem.RefreshableMenuItem} is clicked
     */
    public interface OnRefreshActionListener {
        /**
         * Called when a {@link com.aldoborrero.minirefreshactionitem.RefreshableMenuItem}
         * has been clicked
         *
         * @param sender The view that was clicked
         * @param <T> T must implement RefreshableMenuItem
         */
        <T extends RefreshableMenuItem> void onRefreshButtonClick(T sender);
    }

}
