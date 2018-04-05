package com.lt.qjoke.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lt.qjoke.BR;
import com.lt.qjoke.R;
import com.lt.qjoke.base.BaseActivity;
import com.lt.qjoke.utils.FrescoUtils;
import com.lt.qjoke.utils.NetUtils;
import com.lt.qjoke.view.SettingView;
import com.lt.qjoke.viewmodel.SettingViewModel;


/**
 * Created by LT on 2017/9/21.
 */
public class SettingActivity extends BaseActivity implements SettingView {

    private static final String TAG = "SettingActivity";
    private SettingViewModel mSettingViewModel;

    @Override
    protected void initView() {
        ViewDataBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        mSettingViewModel = new SettingViewModel(this);
        viewDataBinding.setVariable(BR.viewModel, mSettingViewModel);
    }

    @Override
    public void clearCache(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确定要清除缓存?");
        builder.setPositiveButton("取消",null);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FrescoUtils.clearFrescoCaches();
                mSettingViewModel.cacheSize.set("0B");
            }
        });
        builder.create().show();
    }
}
