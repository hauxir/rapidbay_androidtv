package com.rapidbay.rapidbay;

import android.app.Activity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;

class MyChrome extends WebChromeClient {

    private final Activity mainActivity;
    private View mCustomView;
    private CustomViewCallback mCustomViewCallback;
    protected FrameLayout mFullscreenContainer;
    private int mOriginalOrientation;
    private int mOriginalSystemUiVisibility;

    public MyChrome(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }


    public void onHideCustomView() {
        ((FrameLayout) mainActivity.getWindow().getDecorView()).removeView(this.mCustomView);
        this.mCustomView = null;
        mainActivity.getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
        mainActivity.setRequestedOrientation(this.mOriginalOrientation);
        this.mCustomViewCallback.onCustomViewHidden();
        this.mCustomViewCallback = null;
    }

    public void onShowCustomView(View paramView, CustomViewCallback paramCustomViewCallback) {
        if (this.mCustomView != null) {
            onHideCustomView();
            return;
        }
        this.mCustomView = paramView;
        this.mOriginalSystemUiVisibility = mainActivity.getWindow().getDecorView().getSystemUiVisibility();
        this.mOriginalOrientation = mainActivity.getRequestedOrientation();
        this.mCustomViewCallback = paramCustomViewCallback;
        ((FrameLayout) mainActivity.getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
        mainActivity.getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
}
