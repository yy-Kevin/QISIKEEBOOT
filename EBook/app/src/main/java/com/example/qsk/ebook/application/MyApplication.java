package com.example.qsk.ebook.application;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.OkHttpClient;

/**
 * Created by qsk on 2017/3/10.
 */

public class MyApplication extends Application {

    public static OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        client = OkHttpUtils.getInstance().getOkHttpClient();
    }
}
