package com.example.qsk.ebook.bookview;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qsk.ebook.R;
import com.example.qsk.ebook.bean.Books;
import com.example.qsk.ebook.foundviewpager.FoundcontentViewpager;
import com.panxw.android.imageindicator.AutoPlayManager;
import com.panxw.android.imageindicator.ImageIndicatorView;

import java.util.ArrayList;

/**
 * Created by qsk on 2017/3/6.
 * 发现界面的逻辑
 *
 */

public class FoundPager extends BaseBookPager {

    private static final String TAG = "FoundPager";
    private ListView lv_found;
    private ArrayList<Books> books;
    private ArrayList<FoundcontentViewpager> im;
    private View indicate_view;
    private ImageIndicatorView viewpager;

    public FoundPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.viewpager_found,null);
        lv_found = (ListView) view.findViewById(R.id.lv_found);

        indicate_view = View.inflate(mActivity, R.layout.list_found_itme_head,null);
        viewpager = (ImageIndicatorView) indicate_view.findViewById(R.id.vp_found);
        local();
//        im = new ArrayList();
//        im.add(new FoundcontentViewpager(mActivity));
//        im.add(new FoundcontentViewpager(mActivity));
//        im.add(new FoundcontentViewpager(mActivity));
//        im.add(new FoundcontentViewpager(mActivity));
//        viewpager.setAdapter(new FoundViewPagerAdapter());
        lv_found.addHeaderView(indicate_view);

        return view;
    }

    //系统本地图片加载
         public void local() {
                 // 声明一个数组, 指定图片的ID
                 final Integer[] resArray = new Integer[] {R.drawable.back_left, R.drawable.back_left,
                         R.drawable.back_left, R.drawable.back_left};
                 // 把数组交给图片展播组件
                 viewpager.setupLayoutByDrawable(resArray);

//                    viewpager.;
                 // 展播的风格
         //        indicate_view.setIndicateStyle(ImageIndicatorView.INDICATE_ARROW_ROUND_STYLE);
                 viewpager.setIndicateStyle(ImageIndicatorView.INDICATE_USERGUIDE_STYLE);
                 // 显示组件
                 viewpager.show();
                 final AutoPlayManager autoBrocastManager = new AutoPlayManager(viewpager);
                 //设置开启自动广播
                 autoBrocastManager.setBroadcastEnable(true);
                 //autoBrocastManager.setBroadCastTimes(5);//loop times
                 //设置开始时间和间隔时间
                 autoBrocastManager.setBroadcastTimeIntevel(3000, 3000);
                 //设置循环播放
                 autoBrocastManager.loop();
             }

    @Override
    public void initData() {
        Log.i(TAG,"发现--页面开始加载数据了");
        books = new ArrayList();
        books.add(new Books("adb1" , "adbcdfa1" ,"adfasdffasdfasdfsdfdf as 1"));
        books.add(new Books("adb2" , "adbcdfa2" ,"adfasdffasdfasdfsdfdf as 2"));
        books.add(new Books("adb3" , "adbcdfa3" ,"adfasdffasdfasdfsdfdf as 3"));
        books.add(new Books("adb4" , "adbcdfa4" ,"adfasdffasdfasdfsdfdf as 4"));

        lv_found.setAdapter(new FoundListViewAdapter());



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
            FoundcontentViewpager foundcontentViewpager = im.get(position);
            ImageView mRootView = foundcontentViewpager.mRootView;
            container.addView(mRootView);

            return mRootView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    private class FoundListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return books.size();
        }

        @Override
        public Books getItem(int position) {
            return books.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            BookHolder holder = null;
            if (view == null){
                view = View.inflate(mActivity,R.layout.listview_found_item,null);
                holder = new BookHolder();
                view.setTag(holder);
            }else {
                holder = (BookHolder) view.getTag();
                view = convertView;
            }

            holder.title = (TextView) view.findViewById(R.id.tv_title);
            holder.author = (TextView) view.findViewById(R.id.tv_author);
            holder.content = (TextView) view.findViewById(R.id.tv_content);

            holder.title.setText(books.get(position).getTitle());
            holder.author.setText(books.get(position).getAuthor());
            holder.content.setText(books.get(position).getContent());

            return view;
        }

        private class BookHolder{
            TextView author;
            TextView title;
            TextView content;
        }
    }
}
