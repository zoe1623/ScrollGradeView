package com.leo.scrollgradeview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ScrollViewGroup extends FrameLayout {
    public ScrollViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public ScrollViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public ScrollViewGroup(Context context) {
        super(context);
        init();
    }
    private TextView mTextView;
    private ScrollView mScrollView;
    private void init() {
        mTextView = new TextView(getContext());
        mTextView.setText("0.0");
        LayoutParams params = new LayoutParams(-2,-2, Gravity.CENTER_HORIZONTAL);
        params.topMargin = 10;
        addView(mTextView,params);
        mScrollView = new ScrollView(getContext(),new ScrollView.OnScrollListener() {
            @Override
            public void onScroll(int index) {
                mTextView.setText(index/10 + "." +index%10);
            }
        });
        addView(mScrollView);
        addView(new ScrollViewCenter(getContext()));
    }
}
