package com.example.qsk.ebook.foundviewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qsk.ebook.BookAcitvity;
import com.example.qsk.ebook.MainActivity;
import com.example.qsk.ebook.R;
import com.example.qsk.ebook.bean.Books;
import com.example.qsk.ebook.bean.BooksJson;
import com.example.qsk.ebook.bookview.FoundPager;
import com.example.qsk.ebook.hander.ImageHandler;
import com.yalantis.phoenix.PullToRefreshView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by qsk on 2017/3/8.
 *
 * 发现页面的第一个页面
 */

public class FirstFound extends FoundcontentViewpager {

    private ListView lv_listview_first;
    private ArrayList<Books> books;
    public ViewPager viewPager;
    private View view1;
    private ArrayList<ImageView> im;
    private static final String LOG_TAG = "MainActivity";
    private MainActivity mainActivity;
    private SwipeRefreshLayout mSwipeLayout;


    public FirstFound(Activity activity,ArrayList<BooksJson.DataBean> bean) {
        super(activity,bean);
    }

    @Override
    public View intiView() {
        View view = View.inflate(mActivity, R.layout.listview_found_first_item, null);
        lv_listview_first = (ListView) view.findViewById(R.id.lv_listview_first);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        view1 = View.inflate(mActivity, R.layout.viewpager_found_first_item, null);
        viewPager = (ViewPager) view1.findViewById(R.id.view_found_first_item);
        mainActivity = (MainActivity) mActivity;

        im = new ArrayList();

        //初始化轮播图的数据
        for (int i=0;i<3;i++){
//            Glide.with(mActivity).resumeRequests()
//            Glide.with(mActivity)
//                    .load(url)
//                    .placeholder(R.drawable.back_left)
//                    .crossFade()
//                    .into(myImageView);

            ImageView imageView = new ImageView(mActivity);
            imageView.setBackgroundResource(R.drawable.back_left);
            im.add(imageView);
        }

        //初始化listview的item的数据
        books = new ArrayList();
        books.add(new Books("adb1" , "adbcdfa1" ,"adfasdffasdfasdfsdfdf as 1"));
        books.add(new Books("adb2" , "adbcdfa2" ,"adfasdffasdfasdfsdfdf as 2"));
        books.add(new Books("adb3" , "adbcdfa3" ,"adfasdffasdfasdfsdfdf as 3"));
        books.add(new Books("adb4" , "adbcdfa4" ,"adfasdffasdfasdfsdfdf as 4"));
        lv_listview_first.setAdapter(new FoundListViewAdapter());

        viewPager.setAdapter(new FirstFoundAdapter());
        //给listview 添加头布局
        lv_listview_first.addHeaderView(viewPager);



        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeLayout.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
//        mSwipeLayout.setProgressBackgroundColor(R.color.red); // 设定下拉圆圈的背景
        mSwipeLayout.setSize(SwipeRefreshLayout.LARGE); // 设置圆圈的大小
        //设置下拉刷新的的事件
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new FoundPager(mActivity).initData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 停止刷新
                        mSwipeLayout.setRefreshing(false);
                    }
                }, 2000); // 2秒后发送消息，停止刷新
            }
        });
        return view;
    }

    @Override
    public void intiData() {

        //设置Hander 使viewpager一直轮播。
//        mainActivity.ititHander();
        //给listview设置点击事件
        lv_listview_first.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, BookAcitvity.class);
                mActivity.startActivity(intent);
            }
        });
    }

    //viewpager的adapter  实现了无限轮播的效果
    private class FirstFoundAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            //对ViewPager页号求模取出View列表中要显示的项
            position %= im.size();
            if (position<0){
                position = im.size()+position;
            }
            ImageView view = im.get(position);
            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewParent vp =view.getParent();
            if (vp!=null){
                ViewGroup parent = (ViewGroup)vp;
                parent.removeView(view);
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }
    }

    //ListView的Adapter
    private class FoundListViewAdapter extends BaseAdapter {

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
