package com.leo.scrollgradeview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ScrollViewCenter extends View {
    public ScrollViewCenter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public ScrollViewCenter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public ScrollViewCenter(Context context) {
        super(context);
        init();
    }
    private int center = getResources().getDisplayMetrics().widthPixels/2;
    private Paint mPaint;
    private ViewBean mVBG = new ViewBean();
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(3);

        mVBG.endY = mVBG.startY + 100;
        mVBG.color = Color.GRAY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(center,mVBG.startY,center,mVBG.endY, mPaint);
    }
}
