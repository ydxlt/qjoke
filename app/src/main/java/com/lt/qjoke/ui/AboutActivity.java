package com.lt.qjoke.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.widget.TextView;

import com.lt.qjoke.R;
import com.lt.qjoke.base.BaseActivity;
import com.lt.qjoke.databinding.ActivityAboutBinding;
import com.lt.qjoke.utils.AppUtils;
import com.lt.qjoke.viewmodel.AboutViewModel;

/**
 * Created by LT on 2017/9/25.
 */
public class AboutActivity extends BaseActivity {

    @Override
    protected void initView() {
        ActivityAboutBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        viewDataBinding.setViewModel(new AboutViewModel(this));
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setTitle("关于");
    }
}
