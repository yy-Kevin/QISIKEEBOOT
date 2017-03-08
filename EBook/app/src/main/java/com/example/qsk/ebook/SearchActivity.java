package com.example.qsk.ebook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qsk.ebook.view.FlowLayout;

/**
 * Created by qsk on 2017/3/7.
 */

public class SearchActivity extends AppCompatActivity {
    public static final String TAG = "SearchActivity";


    private String[] mVals = new String[] { "苹果手机", "笔记本电脑", "电饭煲 ", "腊肉",
            "特产", "剃须刀", "宝宝", "康佳" };
    private LayoutInflater mInflater;
    private FlowLayout mFlowLayout;
    private EditText et_search;
    private TextView tv_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);
        initView();
        initData();

        //设置搜索界面的监听事件。
        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG,"yuyao EditText 字段开始搜索了");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        et_search.addTextChangedListener(textWatcher);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"点击了返回页面");
                finish();
            }
        });
    }
    //初始化 搜索界面
    public void initView(){
        mInflater = LayoutInflater.from(this);
        mFlowLayout = (FlowLayout) findViewById(R.id.id_flowlayout);
        et_search = (EditText)findViewById(R.id.et_search);
        tv_cancel = (TextView)findViewById(R.id.tv_cancel);
    }

    public void initData() {
        /**
         * 找到搜索标签的控件
         */
        for (int i = 0; i < mVals.length; i++) {
            TextView tv = (TextView) mInflater.inflate(
                    R.layout.search_label_tv, mFlowLayout, false);
            tv.setText(mVals[i]);
            final String str = tv.getText().toString();
            //点击事件
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mFlowLayout.addView(tv);//添加到父View
        }
    }
}
