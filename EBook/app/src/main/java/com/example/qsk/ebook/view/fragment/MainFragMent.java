package com.example.qsk.ebook.view.fragment;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.qsk.ebook.R;
import com.example.qsk.ebook.bookview.BaseBookPager;
import com.example.qsk.ebook.bookview.BookCasePager;
import com.example.qsk.ebook.bookview.FoundPager;
import com.example.qsk.ebook.bookview.MyDePager;
import com.example.qsk.ebook.view.weight.MainViewPager;

import java.util.ArrayList;

/**
 * Created by qsk on 2017/3/6.
 */

public class MainFragMent extends BaseFragment {
    public static final  String TAG = "MainFragMent";

    private MainViewPager viewPager;
    public ArrayList<BaseBookPager> mPagers;
    private RadioGroup rg_main;
    private RadioButton rb_main1;
    private TextView tv_hot;
    private TextView tv_new;
    private TextView tv_haha;

    @Override
    public View initView() {
        View view = View.inflate(mActivity,R.layout.fragment_main,null);
        viewPager = (MainViewPager) view.findViewById(R.id.vp_main_viewpager);
        tv_hot = (TextView) view.findViewById(R.id.tv_hot);
        tv_new = (TextView) view.findViewById(R.id.tv_new);
        tv_haha = (TextView) view.findViewById(R.id.tv_haha);
        rg_main = (RadioGroup) view.findViewById(R.id.rg_main);
        rb_main1 = (RadioButton) view.findViewById(R.id.rb_main1);
        return view;
    }

    @Override
    public void initData() {
        Log.i(TAG, "yuyao MainFragment 页面开始加载数据了");
        //开始请求网络了
//        initInternet();

        //初始化Viewpager的三个页面
        mPagers = new ArrayList<>();
        mPagers.add(new FoundPager(mActivity));
        mPagers.add(new BookCasePager(mActivity));
        mPagers.add(new MyDePager(mActivity));

        //给Viewpager设置Adapter
        viewPager.setAdapter(new MainAdapter());

        //给RadioGroup设置点击事件
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i(TAG,"yuyao " + checkedId);
                switch (checkedId){
                    case R.id.rb_main1:
                        viewPager.setCurrentItem(0,false);
                        tv_title.setText("首页");
                        break;
                    case R.id.rb_main2:
                        tv_title.setText("订阅");
                        viewPager.setCurrentItem(1,false);
                        break;
                    case R.id.rb_main3:
                        tv_title.setText("我的");
                        viewPager.setCurrentItem(2,false);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    //请求网络
    private void initInternet(){

    }

    // 主页面的adapter，发现，书柜，我的。
    public class MainAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseBookPager pager = mPagers.get(position);
            View pagerView = pager.mRootView;
            pager.initData();
            container.addView(pagerView);
            return pagerView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}
