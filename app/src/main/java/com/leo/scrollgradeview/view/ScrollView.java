package com.leo.scrollgradeview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by nannan on 2017/11/9.
 */

public class ScrollView extends View {
    private OnScrollListener mListener;
    public ScrollView(Context context,OnScrollListener l) {
        super(context);
        mListener = l;
        init();
    }
    private Paint mPaint;
    private GestureDetector mGestureDetector;

    private ViewBean mVBG = new ViewBean();
    private ViewBean mVBR = new ViewBean();
    private int center = getResources().getDisplayMetrics().widthPixels/2;
    private float density = getResources().getDisplayMetrics().density;
    private void init() {
        mPaint = new Paint();
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(3);
        mPaint.setTextSize(14*density);

        mGestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                stop = true;
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                Log.e("=========","onShowPress: ");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.e("=========","onSingleTapUp: ");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                move((int) distanceX);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, final float velocityX, float velocityY) {
                Log.e("=========","onFling: " + velocityX);
                stop = false;
                new Thread(){
                    @Override
                    public void run() {
                        Message message = mHandler.obtainMessage();
                        message.what = MSG_FLING;
                        message.arg1 = (int) velocityX/20;
                        mHandler.sendMessage(message);
                    }
                }.start();
                return true;
            }
        });

        mVBG.endY = mVBG.startY + 50;
        mVBG.color = Color.GREEN;
        mVBR.endY = mVBR.startY + 30;
        //move(40*dx*10);//TODO 初始化位置 40.0
    }
    private int move;
    private boolean stop = true;
    private boolean move(int distanceX){
        int tmp = move + distanceX;
        boolean stop = false;
        if(tmp < 0) {
            distanceX = -move;
            stop = true;
        }
        if(tmp > (len -1)*dx) {
            distanceX = (len -1)*dx - move;
            stop = true;
        }
        move += distanceX;
        scrollBy(distanceX, 0);

        if(mListener != null){
            mListener.onScroll(move/dx);
        }
        Log.e("=========","move: " + move);
        return stop;
    }

    private void up(){
        int i = move % dx;
        int i1 = i - dx/2;
        if(i1 < 0){
            move -= i;
            scrollBy(-i, 0);
        }else {
            move += (dx-i);
            scrollBy(dx-i, 0);
        }
        Log.e("=========","move: " + move);

        if(mListener != null){
            mListener.onScroll(move/dx);
        }
    }
    private int len = 1001;
    private int dx = 20;
    private static final int MSG_FLING = 100;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == MSG_FLING){
                int arg1 = msg.arg1;
                if(Math.abs(arg1) < 1 || stop){
                    up();
                    return;
                }
                if(move(-arg1)){
                    return;
                }
                Message message = mHandler.obtainMessage();
                message.what = MSG_FLING;
                message.arg1 = (int) (arg1/1.1);
                mHandler.sendMessageDelayed(message,20);
            }
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            up();
        }
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int distance = center;
        for(int i = 0; i < len; i++){
            if(i%10 == 0) {
                mPaint.setColor(mVBG.color);
                canvas.drawText(i/10 + "", distance,mVBG.endY + 50, mPaint);
                canvas.drawLine(distance,mVBG.startY,distance,mVBG.endY, mPaint);
            } else {
                mPaint.setColor(mVBR.color);
                canvas.drawLine(distance,mVBR.startY,distance,mVBR.endY, mPaint);
            }
            distance += dx;
        }
    }
    public interface OnScrollListener{
        void onScroll(int index);
    }
}
