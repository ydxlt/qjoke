package com.lt.qjoke.retrofit;

import com.lt.qjoke.utils.ApiUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2018/4/4.
 */

public class RetrofitProvider {

    private static Retrofit sInstance;

    private RetrofitProvider(){

    }

    public static Retrofit getInstance(){
        if(sInstance == null) {
            synchronized (RetrofitProvider.class) {
                if(sInstance == null){
                    sInstance = new Retrofit.Builder()
                            .baseUrl(ApiUtils.BASE_URI)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            // 添加RxJava2的支持
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            // 用Gson解析数据
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }else{
            return sInstance;
        }
        return sInstance;
    }
}
