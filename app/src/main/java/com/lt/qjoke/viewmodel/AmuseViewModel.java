package com.lt.qjoke.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.view.LayoutInflater;

import com.lt.qjoke.App;
import com.lt.qjoke.R;
import com.lt.qjoke.base.ViewModel;
import com.lt.qjoke.messenger.Messenger;
import com.lt.qjoke.model.AmuseModel;
import com.lt.qjoke.retrofit.AmuseService;
import com.lt.qjoke.retrofit.RetrofitProvider;
import com.lt.qjoke.utils.ApiUtils;
import com.lt.qjoke.utils.LogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import me.tatarka.bindingcollectionadapter.BaseItemViewSelector;
import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018/4/3.
 */

public class AmuseViewModel implements ViewModel{

    public static final String TOKEN_GET_AMUSE_FINISH = App.sPackageName + "get_amuse_finish";
    private static final java.lang.String TAG = "AmuseViewModel";

    private Context mContext;
    private String mType;

    // view style
    public ViewStyle viewStyle = new ViewStyle();
    public class ViewStyle{
        public final ObservableBoolean isRefreshing = new ObservableBoolean(false);
        public final ObservableBoolean isLoadVisibility = new ObservableBoolean(false);
    }

    // itew view model
    public final ObservableList<AmuseItemViewModel> items = new ObservableArrayList<>();
    // view item layout for recyclerview
    public final ItemViewSelector<AmuseItemViewModel> itemView = new BaseItemViewSelector<AmuseItemViewModel>() {
        @Override
        public void select(ItemView itemView, int position, AmuseItemViewModel item) {
            switch (mType){
                case AmuseService.Amuse.TYPE_EPISODE:
                    itemView.set(com.lt.qjoke.BR.viewModel,R.layout.item_text_amuse);
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


    public AmuseViewModel(Context context, String mType) {
        this.mContext = context;
        this.mType = mType;
        new AmuseModel().getAmuses("", mType, 1, amuse -> items.add(new AmuseItemViewModel(amuse,mContext)));
    }
}
