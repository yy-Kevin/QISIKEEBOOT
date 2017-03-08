package com.example.qsk.ebook.bookview;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qsk.ebook.BookAcitvity;
import com.example.qsk.ebook.R;
import com.example.qsk.ebook.SearchActivity;
import com.example.qsk.ebook.bean.Books;
import com.example.qsk.ebook.bean.BooksJson;
import com.example.qsk.ebook.foundviewpager.FirstFound;
import com.example.qsk.ebook.foundviewpager.FoundcontentViewpager;
import com.example.qsk.ebook.foundviewpager.ThreeFound;
import com.example.qsk.ebook.foundviewpager.TwoFound;
import com.example.qsk.ebook.gloable.NetUrlGloadle;
import com.google.gson.Gson;
import com.panxw.android.imageindicator.AutoPlayManager;
import com.panxw.android.imageindicator.ImageIndicatorView;

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
 * 发现界面的逻辑
 *
 */

public class FoundPager extends BaseBookPager {

    private static final String TAG = "FoundPager";
    public ViewPager lv_found;
    private ArrayList<Books> books;
    private FoundcontentViewpager foundcontentViewpager;

    private View indicate_view;
    private ImageIndicatorView viewpager;
    public ArrayList<FoundcontentViewpager> im;
    private ArrayList<BooksJson.DataBean> dataList;

    public FoundPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.viewpager_found,null);
        lv_found = (ViewPager) view.findViewById(R.id.vp_found);
        return view;
    }


    @Override
    public void initData() {
        initInternet();
        Log.i(TAG,"发现--页面开始加载数据了");
        im = new ArrayList();
        im.add(new FirstFound(mActivity,dataList));
        im.add(new TwoFound(mActivity,dataList));
        im.add(new ThreeFound(mActivity,dataList));
        lv_found.setAdapter(new FoundViewPagerAdapter());

    }

    public void initInternet(){
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
                GsonForNet(books);
            }
        });
    }

    private void GsonForNet(String date){
        Gson gson = new Gson();
        BooksJson booksjson = gson.fromJson(date, BooksJson.class);
         dataList = (ArrayList<BooksJson.DataBean>) booksjson.getData();
        for (BooksJson.DataBean book: booksjson.getData()){
            Log.i(TAG,"book.getAuthor() = " + book.getAuthor());
            Log.i(TAG,"book.getTitle_en = " + book.getTitle_en());
            Log.i(TAG,"book.getTitle_zh(); = " + book.getTitle_zh());
            book.getAuthor();
            book.getTitle_en();
            book.getTitle_zh();
        }
    }

    private class FoundViewPagerAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return im.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            FoundcontentViewpager foundcontentViewpager =  im.get(position);
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
