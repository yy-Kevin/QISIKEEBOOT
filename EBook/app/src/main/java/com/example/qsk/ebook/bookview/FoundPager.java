package com.example.qsk.ebook.bookview;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qsk.ebook.R;
import com.example.qsk.ebook.bean.Books;
import com.example.qsk.ebook.bean.BooksJson;
import com.example.qsk.ebook.foundviewpager.FirstFound;
import com.example.qsk.ebook.foundviewpager.FoundcontentViewpager;
import com.example.qsk.ebook.foundviewpager.ThreeFound;
import com.example.qsk.ebook.foundviewpager.TwoFound;
import com.example.qsk.ebook.view.weight.MyViewPager;
import com.panxw.android.imageindicator.ImageIndicatorView;

import java.util.ArrayList;

/**
 * Created by qsk on 2017/3/6.
 * 发现界面的逻辑
 *
 */

public class FoundPager extends BaseBookPager {

    private static final String TAG = "FoundPager";
    public MyViewPager lv_found;
    private ArrayList<Books> books;
    private FoundcontentViewpager foundcontentViewpager;

    private View indicate_view;
    private ImageIndicatorView viewpager;
    public ArrayList<FoundcontentViewpager> foundList;
    private ArrayList<BooksJson.DataBean> dataList;
    private TextView tv_haha;
    private TextView tv_new;
    private TextView tv_hot;

    public FoundPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.viewpager_found,null);

        //Hot new haha 三个页面的ViewPager
        lv_found = (MyViewPager) view.findViewById(R.id.vp_found_dicator_pager_item);

        tv_hot = (TextView) view.findViewById(R.id.tv_hot);
        tv_new = (TextView) view.findViewById(R.id.tv_new);
        tv_haha = (TextView) view.findViewById(R.id.tv_haha);

        return view;
    }


    @Override
    public void initData() {
//        initInternet();
        Log.i(TAG,"发现--页面开始加载数据了");

        foundList = new ArrayList();
        foundList.add(new FirstFound(mActivity));
        foundList.add(new TwoFound(mActivity));
        foundList.add(new ThreeFound(mActivity));
        lv_found.setAdapter(new FoundViewPagerAdapter());

        // 设置最新 火热 最近浏览记录的点击事件
        tv_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_found.setCurrentItem(0);
            }
        });
        tv_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_found.setCurrentItem(1);
            }
        });
        tv_haha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_found.setCurrentItem(2);
            }
        });
    }

    public void initInternet(){

    }

    private void GsonForNet(String date){

    }

    //最新 火热 头条 三个页面的adapter
    private class FoundViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return foundList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            FoundcontentViewpager foundcontentViewpager =  foundList.get(position);
            View mRootView = foundcontentViewpager.mRootView;
            foundcontentViewpager.intiData();
            container.addView(mRootView);
            return mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

}
