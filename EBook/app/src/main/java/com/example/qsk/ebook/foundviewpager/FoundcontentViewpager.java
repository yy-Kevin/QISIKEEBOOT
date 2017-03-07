package com.example.qsk.ebook.foundviewpager;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.example.qsk.ebook.R;

/**
 * Created by qsk on 2017/3/6.
 */

public class FoundcontentViewpager {

    private Activity mActivity;
    public ImageView mRootView;

    public FoundcontentViewpager(Activity activity){
        mActivity = activity;
        mRootView = (ImageView) intiView();
    }

    public View intiView(){
        ImageView imageView = new ImageView(mActivity);
        imageView.setImageResource(R.drawable.back_left);
        return imageView;
    }
}
