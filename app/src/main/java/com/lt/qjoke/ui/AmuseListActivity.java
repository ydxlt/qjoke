package com.lt.qjoke.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.lt.qjoke.R;
import com.lt.qjoke.base.BaseActivity;
import com.lt.qjoke.databinding.ActivityAmuseListBinding;
import com.lt.qjoke.retrofit.AmuseService;
import com.lt.qjoke.viewmodel.AmuseListViewModel;
import com.lt.qjoke.viewmodel.AmuseViewModel;

public class AmuseListActivity extends BaseActivity {
    @Override
    protected void initView() {
        ActivityAmuseListBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_amuse_list);
        viewDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AmuseService.Amuse amuse = (AmuseService.Amuse) getIntent().getSerializableExtra("amuse");
        AmuseListViewModel amuseListViewModel = new AmuseListViewModel(this,amuse);
        viewDataBinding.setViewModel(amuseListViewModel);
    }
}