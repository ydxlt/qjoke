package com.lt.qjoke.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.lt.qjoke.R;

public abstract class BaseActivity extends AppCompatActivity {

    private Snackbar mSnackbar;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configStatusBar();
        init();
        initView();
        initToolbar();
        initData();
    }

    protected void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void configStatusBar() {
        Window window = getWindow();
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
            ViewCompat.setFitsSystemWindows(mChildView, true);
        }
    }

    protected void init() {

    }

    protected abstract void initView();

    protected void initData() {

    }

    public void showToast(String msg) {
        if(mSnackbar == null) {
            synchronized (this){
                if(mSnackbar == null) {
                    mSnackbar = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT);
                }
            }
        }else{
            mSnackbar.setText(msg);
            mSnackbar.show();
        }
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }
}