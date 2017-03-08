package com.example.qsk.ebook.foundviewpager;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.example.qsk.ebook.R;
import com.example.qsk.ebook.bean.BooksJson;

import java.util.ArrayList;

/**
 * Created by qsk on 2017/3/6.
 */

public abstract class FoundcontentViewpager {

    public Activity mActivity;
    public View mRootView;

    public FoundcontentViewpager(Activity activity,ArrayList<BooksJson.DataBean> bean){
        mActivity = activity;
        mRootView = intiView();
    }

    public abstract View intiView();
    public abstract void intiData();
}
