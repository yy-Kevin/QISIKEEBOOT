package com.example.qsk.ebook.view.activity;


import android.content.Intent;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qsk.ebook.R;
import com.example.qsk.ebook.bookview.FoundPager;
import com.example.qsk.ebook.foundviewpager.FirstFound;
import com.example.qsk.ebook.view.fragment.LeftFragMent;
import com.example.qsk.ebook.view.fragment.MainFragMent;
import com.example.qsk.ebook.hander.ImageHandler;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;
    private RelativeLayout left;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private MainFragMent fragmentMain;
    private LeftFragMent fragMentLeft;
    private TextView tv_title;
    public static ImageHandler handler;
    public static FirstFound firstFound;
    private MainFragMent mainFragMent;
    private FoundPager foundPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //初始化布局
        initView();

        //初始化抽屉布局，DrawerLayout
        initDraverToggle();

        //设置ToolBar的标题，此处用法为去掉标题
        actionBar.setTitle("");

        //使用Fragment布局，替换之前的
        initFragment();


    }

    private void initView(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        left = (RelativeLayout) findViewById(R.id.left);
        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        tv_title = (TextView) this.findViewById(R.id.tv_title);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        //设置ToolBar,使ToolBar生效
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void initDraverToggle(){
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initFragment(){
        //新建Fragment
        fragmentMain = new MainFragMent();
        fragMentLeft = new LeftFragMent();

        //将主页面的布局替换成 Fragment布局
        getSupportFragmentManager().beginTransaction().replace(R.id.main, fragmentMain,"MAIN_FRAGMENT").commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.left, fragMentLeft,"LeftFragment").commit();
    }

    //初始化 Toolbar的菜单布局
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    // 设置 ToolBar菜单的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int it = item.getItemId();
        switch (item.getItemId()){

            case R.id.toolbar_action:
//                mDrawerLayout.openDrawer(Gravity.LEFT);
//                Toast.makeText(getApplicationContext(),"点击设置了",Toast.LENGTH_LONG).show();
                Log.i(TAG,"yuyao = "    + "搜索界面打开了");
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
                return true;
            case 16908332:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
