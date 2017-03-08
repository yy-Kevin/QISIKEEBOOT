package com.example.qsk.ebook.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.qsk.ebook.R;
import com.example.qsk.ebook.bookview.BaseBookPager;
import com.example.qsk.ebook.bookview.BookCasePager;
import com.example.qsk.ebook.bookview.FoundPager;
import com.example.qsk.ebook.bookview.MyDePager;
import com.example.qsk.ebook.gloable.NetUrlGloadle;
import com.example.qsk.ebook.view.MainViewPager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        Log.i(TAG,"yuyao " + "初始化几次");
//        mPagers.get(0).initData();

        // 设置最新 火热 最近浏览记录的点击事件
        tv_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoundPager foundPager = (FoundPager) mPagers.get(0);
                foundPager.lv_found.setCurrentItem(0);
            }
        });
        tv_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoundPager foundPager = (FoundPager) mPagers.get(0);
                foundPager.lv_found.setCurrentItem(1);
            }
        });
        tv_haha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoundPager foundPager = (FoundPager) mPagers.get(0);
                foundPager.lv_found.setCurrentItem(2);
            }
        });

    }

    private void initInternet(){
        Log.i(TAG,"yuyao initIntenet " + "开始请求网络了 。。。。。。。。");
        File httpCacheDirectory = new File(mActivity.getExternalCacheDir(), "responses");
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        OkHttpClient client = new OkHttpClient.Builder().cache(cache).build();

        //使用Okhttp请求网络
        Request request = new Request.Builder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .cacheControl(new CacheControl.Builder().maxAge(0, TimeUnit.SECONDS).build())
                .url(NetUrlGloadle.NET_URL)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG,"yuyao onFailure " + "请求网络失败了");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String books = response.body().string();
                    Log.i(TAG,"yuyao onResponse 请求网络成功 1111=" + books);
            }
        });
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
