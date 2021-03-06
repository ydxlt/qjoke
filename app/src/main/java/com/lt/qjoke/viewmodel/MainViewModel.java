package com.lt.qjoke.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.lt.qjoke.App;
import com.lt.qjoke.R;
import com.lt.qjoke.base.BaseActivity;
import com.lt.qjoke.base.ViewModel;
import com.lt.qjoke.messenger.Messenger;
import com.lt.qjoke.model.AmuseModel;
import com.lt.qjoke.retrofit.AmuseService;
import com.lt.qjoke.ui.AmuseListActivity;
import com.lt.qjoke.utils.LogUtils;
import com.lt.qjoke.view.SettingView;

import java.io.Serializable;

import me.tatarka.bindingcollectionadapter.BaseItemViewSelector;
import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;

/**
 * Created by admin on 2018/4/3.
 */

public class MainViewModel implements ViewModel {

    public static String TOKEN_QUERY_FINISH = App.sPackageName + "_token_query_finish";

    private Context mContext;
    private String mType;

    // itew view model
    public final ObservableList<AmuseItemViewModel> items = new ObservableArrayList<>();
    // view item layout for recyclerview
    public final ItemViewSelector<AmuseItemViewModel> itemView = new BaseItemViewSelector<AmuseItemViewModel>() {
        @Override
        public void select(ItemView itemView, int position, AmuseItemViewModel item) {
            switch (mType){
                case AmuseService.Amuse.TYPE_EPISODE:
                    itemView.set(com.lt.qjoke.BR.viewModel, R.layout.item_text_amuse);
                    break;
                case AmuseService.Amuse.TYPE_IMAGE:
                    itemView.set(com.lt.qjoke.BR.viewModel,R.layout.item_image_amuse);
                    break;
                case AmuseService.Amuse.TYPE_VIDEO:
                    itemView.set(com.lt.qjoke.BR.viewModel,R.layout.item_video_amuse);
                    break;
                case AmuseService.Amuse.TYPE_VOICE:
                    itemView.set(com.lt.qjoke.BR.viewModel,R.layout.item_voice_amuse);
                    break;
            }
        }
    };

    public MainViewModel(Context context) {
        mContext = context;
    }

    public void queryAmuse(String query) {
        new AmuseModel().queryAmuse(query,"",1, amuse -> {
                if(amuse == null){
                    ((BaseActivity)mContext).showToast("没有搜索到相关的笑话~");
                    return;
                }
            Intent intent = new Intent(mContext, AmuseListActivity.class);
            intent.putExtra("amuse",amuse);
            mContext.startActivity(intent);
        }, data -> {
            LogUtils.d(TOKEN_QUERY_FINISH,TOKEN_QUERY_FINISH);
        });
    }
}
