package com.example.qsk.ebook.bookview;

import android.app.Activity;
import android.view.View;

/**
 * Created by qsk on 2017/3/6.
 */

public abstract class BaseBookPager {

    public final Activity mActivity;
    public final View mRootView;

    public BaseBookPager(Activity activity){
        mActivity = activity;
        mRootView = initView();
    }
    public abstract View initView();
    public abstract void initData();
}
