package com.lt.qjoke.viewmodel;

import android.databinding.ObservableField;

import com.lt.qjoke.base.ViewModel;

/**
 * Created by admin on 2018/4/3.
 */

public class MainViewModel implements ViewModel {

    public final ObservableField<Boolean> isRefreshing = new ObservableField<>();

    public MainViewModel() {
        isRefreshing.set(true);
    }
}
