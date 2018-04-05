package com.lt.qjoke.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.lt.qjoke.R;
import com.lt.qjoke.base.ViewModel;
import com.lt.qjoke.messenger.Messenger;
import com.lt.qjoke.retrofit.AmuseService;
import com.lt.qjoke.utils.LogUtils;

import me.tatarka.bindingcollectionadapter.BaseItemViewSelector;
import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;
import rx.Observable;

public class AmuseListViewModel implements ViewModel {

    private static final java.lang.String TAG = AmuseListViewModel.class.getSimpleName();
    private Context mContext;
    private AmuseService.Amuse mData;

    // itew view model
    public final ObservableList<AmuseItemViewModel> items = new ObservableArrayList<>();
    // view item layout for recyclerview
    public final ItemViewSelector<AmuseItemViewModel> itemView = new BaseItemViewSelector<AmuseItemViewModel>() {
        @Override
        public void select(ItemView itemView, int position, AmuseItemViewModel item) {
            switch (item.mData.getType()){
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


    public AmuseListViewModel(Context context, AmuseService.Amuse data) {
        mContext = context;
        mData = data;
        Observable.just(data)
                .flatMap(content -> Observable.from(data.getShowapi_res_body().getPagebean().getContentlist()))
                .subscribe( c -> items.add(new AmuseItemViewModel(c,mContext)));
    }
}