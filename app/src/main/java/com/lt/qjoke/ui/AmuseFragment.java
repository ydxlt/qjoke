package com.lt.qjoke.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lt.qjoke.R;
import com.lt.qjoke.databinding.FragmentAmuseBinding;
import com.lt.qjoke.viewmodel.AmuseViewModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/4/3.
 */

public class AmuseFragment extends Fragment {


    protected static final String TAG = "AmuseFragment";

    protected RecyclerView mRecyclerView;
    protected int mPage = 1;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected boolean mIsLoadMore = false;
    private String mType;
    private View mLoadingView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getString("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAmuseBinding amuseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_amuse, container, false);
        amuseBinding.setViewModel(new AmuseViewModel(getActivity(),mType));
        initView(amuseBinding);
        return amuseBinding.getRoot();
    }

    private void initView( ViewDataBinding amuseBinding) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) amuseBinding.getRoot();
//        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mRecyclerView = (RecyclerView) mSwipeRefreshLayout.findViewById(R.id.recyclerView);
        mLoadingView = (LinearLayout) mSwipeRefreshLayout.findViewById(R.id.loadingView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public static Map<String,AmuseFragment> amusePages= new HashMap<String,AmuseFragment>();
    // 工厂方法模式
    public static AmuseFragment newInstance(String type){
        AmuseFragment fragment = amusePages.get(type);
        if(fragment != null){
            return fragment;
        }else {
            fragment = new AmuseFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", type);
            fragment.setArguments(bundle);
            amusePages.put(type,fragment);
            return fragment;
        }
    }
}
