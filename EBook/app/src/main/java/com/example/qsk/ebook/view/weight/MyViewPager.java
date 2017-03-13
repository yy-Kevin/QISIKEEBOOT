package com.example.qsk.ebook.view.weight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

/**
 * Created by qsk on 2017/3/9.
 */

public class MyViewPager extends ViewPager {

    private static final String TAG = "MyViewPager";
    private float x;
    private float mInitialDownY;
    private int mTouchSlop;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

//        final int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                mInitialDownY = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                final float yDiff = ev.getY() - mInitialDownY;
//                if (yDiff < mTouchSlop) {
//                    return false;
//                }
//        }
        return false;
    }


    /**
     * @return 返回灵敏度数值
     */
    public int getTouchSlop() {
        return mTouchSlop;
    }

    /**
     * 设置下拉灵敏度
     *
     * @param mTouchSlop dip值
     */
    public void setTouchSlop(int mTouchSlop) {
        this.mTouchSlop = mTouchSlop;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
//
//        getParent().requestDisallowInterceptTouchEvent(true);
//        float x1 = 0;
//        float x2 = 0;
//        float y1 = 0;
//        float y2 = 0;
//
//        //继承了Activity的onTouchEvent方法，直接监听点击事件
//        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
//            //当手指按下的时候
//            x1 = ev.getX();
//            y1 = ev.getY();
//        }
//        if(ev.getAction() == MotionEvent.ACTION_UP) {
//            //当手指离开的时候
//            x2 = ev.getX();
//            y2 = ev.getY();
//            if(y1 - y2 > 50) {
////                Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
//            } else if(y2 - y1 > 50) {
////                Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
//                return true;
//            } else if(x1 - x2 > 50) {
////                Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
//            } else if(x2 - x1 > 50) {
//
////                Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
//            }
//        }
        return true;
    }
}

