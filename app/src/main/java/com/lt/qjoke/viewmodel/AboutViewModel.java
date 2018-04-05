package com.lt.qjoke.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import com.lt.qjoke.base.ViewModel;
import com.lt.qjoke.utils.AppUtils;

public class AboutViewModel implements ViewModel {

    private Context mContext;
    public final ObservableField<String> version = new ObservableField<>();

    public AboutViewModel(Context context) {
        mContext = context;
        initData();
    }

    private void initData() {
        version.set("v"+AppUtils.getVersionName(mContext));
    }
}