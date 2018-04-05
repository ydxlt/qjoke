package com.lt.qjoke.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageButton;

import com.lt.qjoke.R;
import com.lt.qjoke.base.ViewModel;
import com.lt.qjoke.command.ReplyCommand;
import com.lt.qjoke.utils.AppUtils;
import com.lt.qjoke.utils.FrescoUtils;
import com.lt.qjoke.view.SettingView;

public class SettingViewModel implements ViewModel {

    private Context mContext;
    public final ObservableField<String> version = new ObservableField<>();
    public final ObservableField<String> cacheSize = new ObservableField<>();

    public final ReplyCommand<View> onClickCommand = new ReplyCommand<View>((v)->{
        // view onClick something
        switch (v.getId()){
            case R.id.ll_update:
                // TODO
                break;
            case R.id.ll_clear_cache:
                ((SettingView)mContext).clearCache();
                break;
        }});

    public SettingViewModel(Context context) {
        mContext = context;
        initData();
    }

    private void initData() {
        version.set("v"+AppUtils.getVersionName(mContext));
        cacheSize.set(FrescoUtils.getCacheSize());
    }
}