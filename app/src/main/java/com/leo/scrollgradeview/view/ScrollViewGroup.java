package com.leo.scrollgradeview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import static com.leo.scrollgradeview.view.Constants.DDX;

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
    private void init() {
        mTextView = new TextView(getContext());
        mTextView.setText("0.0");
        LayoutParams params = new LayoutParams(-2,-2, Gravity.CENTER_HORIZONTAL);
        params.topMargin = 10;
        addView(mTextView,params);
        addView(new ScrollView(getContext(),new ScrollView.OnScrollListener() {
            @Override
            public void onScroll(int index) {
                mTextView.setText(index/DDX + "." +index%DDX);
            }
        }));
        addView(new ScrollViewCenter(getContext()), params);

    }

    public String getSelectedValue(){
        return mTextView.getText().toString();
    }
}
