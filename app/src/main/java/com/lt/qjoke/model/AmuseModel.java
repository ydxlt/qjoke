package com.lt.qjoke.model;

import com.lt.qjoke.retrofit.AmuseService;
import com.lt.qjoke.retrofit.RetrofitProvider;
import com.lt.qjoke.utils.ApiUtils;
import com.lt.qjoke.viewmodel.AmuseItemViewModel;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018/4/4.
 */

public class AmuseModel {

    /**
     * 请求网络加载数据
     * @param title
     * @param type
     * @param page
     * @param action1 处理的事物
     */
    public void getAmuses(String title, String type, int page, Action1<AmuseService.Amuse.ResBody.PageBean.Content> action1) {
        Map<String, String> params = new HashMap<String,String>();
        params.put("title",title);
        params.put("type",type);
        params.put("page",page+"");
        params.put("showapi_sign", ApiUtils.SHOWAPI_SECRET);
        params.put("showapi_appid",ApiUtils.SHOWAPI_APPID);
        RetrofitProvider.getInstance().create(AmuseService.class)
                .getAmuseList(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程中进行
                .flatMap( amuse -> Observable.from(amuse.getShowapi_res_body().getPagebean().getContentlist()))
                .subscribe(action1);
    }
}
