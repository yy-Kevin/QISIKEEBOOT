package com.example.qsk.ebook.foundviewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.qsk.ebook.hander.ImageHandler;
import com.example.qsk.ebook.view.activity.BookAcitvity;
import com.example.qsk.ebook.view.activity.MainActivity;
import com.example.qsk.ebook.R;
import com.example.qsk.ebook.bean.Books;
import com.example.qsk.ebook.bean.BooksJson;
import com.example.qsk.ebook.gloable.NetUrlGloadle;
import com.example.qsk.ebook.view.weight.TouchSlopView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.io.File;
import java.util.ArrayList;

import okhttp3.Cache;
import okhttp3.Call;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by qsk on 2017/3/8.
 *
 *
 * 发现页面的第一个页面
 */

public class FirstFound extends FoundcontentViewpager {

    private static final String LOG_TAG = "MainActivity";

    private ListView lv_listview_first;
    private ArrayList<Books> books;
    public ViewPager viewPager;
    private View view_viewpager;
    private ArrayList<ImageView> image_viewpager;
    private MainActivity mainActivity;
    private TouchSlopView mSwipeLayout;
    private ArrayList<BooksJson.DataBean> dataList;
    private ImageView imageView;

    public ImageHandler imageHandler = new ImageHandler(this){};
//    private Handler handler = new Handler() {
//        @Override
//        //当有消息发送出来的时候就执行Handler的这个方法
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            switch (msg.what) {
//
//                case NetUrlGloadle.HANDLER_NET:
//                    for (BooksJson.DataBean bean: dataList){
////                        books.add(new Books((String) bean.getAuthor(), bean.getTitle_en(), (String)bean.getIntroduction()));
//                    }
////                    lv_listview_first.setAdapter(new FoundListViewAdapter());
//                case NetUrlGloadle.HANDLER_NET_ERROR:
//
//
////                           Log.i(TAG,"失败填充的数据");
////                        books.add(new Books("haha","haha","haha"));
////                        books.add(new Books("haha","haha","haha"));
////                        books.add(new Books("haha","haha","haha"));
//
//
//
//                    break;
//                default:
//                    break;
//            }
//        }
//    };

    public FirstFound(Activity activity) {
        super(activity);
    }

    @Override
    public View intiView() {
        View view = View.inflate(mActivity, R.layout.listview_found_first_item, null);
        lv_listview_first = (ListView) view.findViewById(R.id.lv_listview_first);
        mSwipeLayout = (TouchSlopView) view.findViewById(R.id.swipe_container);
        //轮播轮的跟布局
        view_viewpager = View.inflate(mActivity, R.layout.viewpager_found_first_item, null);
        viewPager = (ViewPager) view_viewpager.findViewById(R.id.view_found_first_item);
        mainActivity = (MainActivity) mActivity;
        books = new ArrayList();
        image_viewpager = new ArrayList();

        //初始化listview的item的数据
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeLayout.setDistanceToTriggerSync(200);// 设置手指在屏幕下拉多少距离会触发下拉刷新
//        mSwipeLayout.setProgressBackgroundColor(R.color.red); // 设定下拉圆圈的背景
        mSwipeLayout.setSize(SwipeRefreshLayout.LARGE); // 设置圆圈的大小
        //设置下拉刷新的的事件
        mSwipeLayout.setTouchSlop(350);//设置下拉刷新灵敏度

        for (int i = 0; i < 3; i++) {
            imageView = new ImageView(mainActivity);
            Glide.with(mainActivity)
                    .load(R.drawable.back_left)
                    .into(imageView);
            image_viewpager.add(imageView);
        }

        //给listview 添加头布局
        lv_listview_first.addHeaderView(view_viewpager);

        return view;
    }

    @Override
    public void intiData() {
        Log.i(TAG, "yuyao FirstFound 页面开始加载数据了");

        //请求网络
        initInternet();

        //轮播图的ViewPager
        viewPager.setAdapter(new FirstFoundAdapter());

        //给listview设置点击事件
        lv_listview_first.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i(TAG,"ListView的第 " + (position - 1) + "个条目被点击了");
                Intent intent = new Intent(mainActivity, BookAcitvity.class);
                //通过，intent发送点击书本的详细信息 可以是链接
                intent.putExtra("book_url",dataList.get(position - 1).getLinks().getSelf());
                mActivity.startActivity(intent);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                imageHandler.sendMessage(Message.obtain(imageHandler, imageHandler.MSG_PAGE_CHANGED, position, 0));

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        imageHandler.sendEmptyMessage(imageHandler.MSG_KEEP_SILENT);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        imageHandler.sendEmptyMessageDelayed(imageHandler.MSG_UPDATE_IMAGE, imageHandler.MSG_DELAY);
                        break;
                    default:
                        break;
                }
            }
        });


        viewPager.setCurrentItem(image_viewpager.size()*10000);//默认在中间，使用户看不到边界
        //开始轮播效果
        imageHandler.sendEmptyMessageDelayed(imageHandler.MSG_UPDATE_IMAGE, imageHandler.MSG_DELAY);

        // 给下拉刷新设置回调
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                new FoundPager(mActivity).initData();

//                books = new ArrayList<Books>();
                initInternet();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG,"下拉刷新成功");
                        // 停止刷新
                        mSwipeLayout.setRefreshing(false);
                    }
                }, 2000); // 2秒后发送消息，停止刷新
            }
        });
    }


    /**
     * viewpager的adapter  实现了无限轮播的效果
     *
     */
    private class FirstFoundAdapter extends PagerAdapter {

        @Override
        public int getCount() {

            //设置成最大，使用户看不到边界
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            ImageView view = image_viewpager.get(position);
            //对ViewPager页号求模取出View列表中要显示的项
            position %= image_viewpager.size();
            if (position < 0) {
                position = image_viewpager.size() + position;
            }
            ImageView view = image_viewpager.get(position);
            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewParent vp = view.getParent();
            if (vp != null) {
                ViewGroup parent = (ViewGroup) vp;
                parent.removeView(view);
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
        }
    }


    /**
     * lv_listview_first(listView) 的 Adapter  Fistfound页面
     *
     */
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
            if (view == null) {
                view = View.inflate(mActivity, R.layout.listview_found_item, null);
                holder = new BookHolder();
                view.setTag(holder);
            } else {
                holder = (BookHolder) view.getTag();
                view = convertView;
            }
            holder.title = (TextView) view.findViewById(R.id.tv_author_item_book);
            holder.author = (TextView) view.findViewById(R.id.tv_author_item_book);
            holder.content = (TextView) view.findViewById(R.id.tv_content_item_book);
            holder.imageView = (ImageView) view.findViewById(R.id.iv_bookcase_item_book);

            holder.title.setText(books.get(position).getTitle());
            holder.author.setText(books.get(position).getAuthor());
            holder.content.setText(books.get(position).getContent());

            Glide.with(mainActivity)
                    .load(books.get(position).getImUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView);

            return view;
        }

        private class BookHolder {
            TextView author;
            TextView title;
            TextView content;
            ImageView imageView;
        }
    }

    /**
     * 加载网络的方法
     *
     */
    public void initInternet() {
        Log.i(TAG, "yuyao initIntenet " + "开始请求网络了 。。。。。。。。");

        File httpCacheDirectory = new File(mActivity.getExternalCacheDir(), "responses");
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        OkHttpUtils.getInstance().getOkHttpClient().newBuilder().cache(cache);
        OkHttpUtils
                .get()
                .url(NetUrlGloadle.NET_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "yuyao onResponse 请求网络失败 1111=");
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Log.i(TAG, "yuyao onResponse 请求网络成功 1111=" + response);

                        //使用Gson解析数据
                        GsonForNet(response);

                        //使用RxJava完成异步使用
                        Observable.just(response)
                                .subscribeOn(Schedulers.immediate()) // 指定 在当前线程完成
                                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                                .subscribe(new Action1<String>() {
                                    @Override
                                    public void call(String number) {
                                        Log.d(TAG, "number:" + number);
                                        if (books.size() != 0){
                                            books.clear();
                                        }
                                        for (BooksJson.DataBean bean: dataList){

                                                books.add(new Books((String) bean.getAuthor(),
                                                        bean.getTitle_en(), (String)bean.getIntroduction(),(String) bean.getCover()));
                                            }
                                        lv_listview_first.setAdapter(new FoundListViewAdapter());
                                    }
                                });
                    }

                });
    }

    public void initRxJava(){

        /**
         * 打印helloworld，使用被观察者和订阅者
         * 被观察者发出事件信息
         * 订阅者处理事件
         */
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i("subscriber", "complete");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("subscriber", s);
            }
        };
        observable.subscribe(subscriber);
    }


    /**
     * @param date 请求网络成功的数据
     *
     * 使用JSON来解析数据
     */
    private void GsonForNet(String date) {
        Log.i(TAG, "yuyao , 使用JSON开始解析数据了");
        Gson gson = new Gson();
        BooksJson booksjson = gson.fromJson(date, BooksJson.class);
        dataList = (ArrayList<BooksJson.DataBean>) booksjson.getData();
    }
}
