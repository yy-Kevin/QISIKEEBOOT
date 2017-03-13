package com.example.qsk.ebook.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qsk.ebook.R;
import com.example.qsk.ebook.bean.BookSingle;
import com.example.qsk.ebook.bean.BookSingleJson;
import com.example.qsk.ebook.bean.BooksJson;
import com.example.qsk.ebook.gloable.NetUrlGloadle;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.io.File;
import java.util.ArrayList;

import okhttp3.Cache;
import okhttp3.Call;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by qsk on 2017/3/7.
 */

public class BookAcitvity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "BookActivity";
    private ImageView iv_book_bokactivity;
    private TextView tv_author_book_bokactivity;
    private TextView tv_name_book_bokactivity;
    private TextView tv_look_book_bokactivity;
    private TextView tv_stop_book_bokactivity;
    private TextView bt_read_book_bookactivity;
    private TextView tv_loading_book_bookactivity;
    private TextView tv_finish_book_bookactivity;
    private TextView tv_update_book_bookactivity;
    private String nameUrl;
    private BookSingleJson.DataBean bookSingles;
    private ArrayList<BookSingle> bookSingleList;

    // 设置折叠动画 书的简介
    private TextView descriptionView;
    private View layoutView; //LinearLayout布局
    private View expandView; //和ImageView
    int maxDescripLine = 3; //TextView默认最大展示行数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //加载数据
        initData();

        //请求网络
        initInternet();
    }
    private void initView(){
        setContentView(R.layout.acitvity_book);
        iv_book_bokactivity = (ImageView) findViewById(R.id.iv_book_bokactivity);
        tv_author_book_bokactivity = (TextView) findViewById(R.id.tv_author_book_bokactivity);
        tv_name_book_bokactivity = (TextView) findViewById(R.id.tv_name_book_bokactivity);
        tv_look_book_bokactivity = (TextView) findViewById(R.id.tv_look_book_bokactivity);
        tv_stop_book_bokactivity = (TextView) findViewById(R.id.tv_stop_book_bokactivity);
        bt_read_book_bookactivity = (TextView) findViewById(R.id.bt_read_book_bookactivity);
        tv_loading_book_bookactivity = (TextView) findViewById(R.id.tv_loading_book_bookactivity);
        tv_finish_book_bookactivity = (TextView) findViewById(R.id.tv_finish_book_bookactivity);
        tv_update_book_bookactivity = (TextView) findViewById(R.id.tv_update_book_bookactivity);

        // 简介模块的初始化
        layoutView = findViewById(R.id.description_layout);
        descriptionView = (TextView)findViewById(R.id.description_view);
        expandView = findViewById(R.id.expand_view);


        //下载图书
        tv_loading_book_bookactivity.setOnClickListener(this);
        //书的完结
        tv_finish_book_bookactivity.setOnClickListener(this);


        //descriptionView设置默认显示高度
        descriptionView.setHeight(descriptionView.getLineHeight() * maxDescripLine);

        //根据高度来判断是否需要再点击展开， 后续使用RXjava
        descriptionView.post(new Runnable() {
            @Override
            public void run() {
                expandView.setVisibility(descriptionView.getLineCount() > maxDescripLine ? View.VISIBLE : View.GONE);
            }
        });

        // 设置简介展开和缩放的动画
        setAnimailOnIntroduction();
        // 设置书的各个属性
        setBookAttribute();


    }

    private void setBookAttribute(){

        BookSingle bookSingle = bookSingleList.get(0);

        //设置书的作者
        tv_author_book_bokactivity.setText(bookSingleList.get(0).getAuthor());

        //设置书的简介
        descriptionView.setText(bookSingle.getChapter());

        //设置书的封面
        Glide.with(this).load(bookSingle.getImUrl()).into(iv_book_bokactivity);

        //设置书的名字
        tv_name_book_bokactivity.setText(bookSingle.getBookName());

        //设置书的跟新时间
        tv_update_book_bookactivity.setText("跟新至:" + bookSingles.getUpdate_timestamp() + ", 共" + + bookSingles.getUpdate_timestamp() + "章");

    }

    private void setAnimailOnIntroduction(){
        layoutView.setOnClickListener(new View.OnClickListener() {
            boolean isExpand;//是否已展开的状态

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                descriptionView.clearAnimation();//清楚动画效果
                final int deltaValue;//默认高度，即前边由maxLine确定的高度
                final int startValue = descriptionView.getHeight();//起始高度
                int durationMillis = 350;//动画持续时间
                if (isExpand) {
                    /**
                     * 折叠动画
                     * 从实际高度缩回起始高度
                     */
                    deltaValue = descriptionView.getLineHeight() * descriptionView.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                } else {
                    /**
                     * 展开动画
                     * 从起始高度增长至实际高度
                     */
                    deltaValue = descriptionView.getLineHeight() * maxDescripLine - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                        descriptionView.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }
                };
                animation.setDuration(durationMillis);
                descriptionView.startAnimation(animation);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //书的封面
            case R.id.iv_book_bokactivity:

                break;
            //书的作者
            case R.id.tv_author_book_bokactivity:
                break;
            //输的名字
            case R.id.tv_name_book_bokactivity:

                break;
            //书的查看量
            case R.id.tv_look_book_bokactivity:

                break;
            //书的点击量
            case R.id.tv_stop_book_bokactivity:

                break;
            //书的阅读量
            case R.id.bt_read_book_bookactivity:

                break;

            //下载图书
            case R.id.tv_loading_book_bookactivity:

                break;
        }

    }

    private void initInternet() {
        File httpCacheDirectory = new File(this.getExternalCacheDir(), "responses");
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
//        OkHttpUtils.getInstance().getOkHttpClient().newBuilder().cache(cache);
        OkHttpUtils
                .get()
                .url(nameUrl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        GsonForNet(response);
                        Observable.just(response)
                                .subscribeOn(Schedulers.immediate()) // 指定 subscribe()
                                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                                .subscribe(new Action1<String>() {
                                    @Override
                                    public void call(String number) {
                                        Log.d(TAG, "number:" + number);
                                        bookSingleList = new ArrayList<BookSingle>();
                                        bookSingleList.add(new BookSingle(bookSingles.getAuthor(),bookSingles.getTitle_zh(),bookSingles.getCover(),
                                                bookSingles.getIntroduction(),bookSingles.getAuthor(),bookSingles.getAuthor()));
                                        initView();

                                    }
                                });
                    }

                });
    }

    private void initData(){
        Intent intent=getIntent();
        nameUrl = intent.getStringExtra("book_url");
        Log.i(TAG,"name  url" + nameUrl);
    }

    /**
     * @param date 请求网络成功的数据
     *
     * 使用JSON来解析数据
     */
    private void GsonForNet(String date) {
        Log.i(TAG, "yuyao , 使用JSON开始解析数据了");
        Gson gson = new Gson();
        BookSingleJson booksjson = gson.fromJson(date, BookSingleJson.class);
        bookSingles = booksjson.getData();
    }
}
