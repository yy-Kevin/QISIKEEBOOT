package com.example.qsk.ebook.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.panxw.android.imageindicator.ImageIndicatorView;

/**
 * Created by qsk on 2017/3/7.
 */

public class DicatorViewPager extends ImageIndicatorView {
    public DicatorViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DicatorViewPager(Context context) {
        super(context);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        getParent().requestDisallowInterceptTouchEvent(true);
//        return super.dispatchTouchEvent(ev);
//    }
}
