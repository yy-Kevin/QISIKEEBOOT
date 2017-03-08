package com.example.qsk.ebook.foundviewpager;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.qsk.ebook.bean.BooksJson;

import java.util.ArrayList;

/**
 * Created by qsk on 2017/3/8.
 *
 * 发现页面的第三个页面
 */

public class ThreeFound extends FoundcontentViewpager{
    public ThreeFound(Activity activity,ArrayList<BooksJson.DataBean> bean) {
        super(activity,bean);
    }

    @Override
    public View intiView() {
        TextView textView = new TextView(mActivity);
        textView.setText("第三个页面");
        return textView;
    }

    @Override
    public void intiData() {

    }
}
