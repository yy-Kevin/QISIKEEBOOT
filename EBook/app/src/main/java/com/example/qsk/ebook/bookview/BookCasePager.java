package com.example.qsk.ebook.bookview;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by qsk on 2017/3/6.
 */

public class BookCasePager extends BaseBookPager {

    private static final String TAG = "BookCasePager";

    public BookCasePager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(mActivity);
        textView.setText("书柜页面");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        Log.i(TAG,"书柜页面开始加载数据了");
    }
}
