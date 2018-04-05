package com.lt.qjoke.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.lt.qjoke.R;
import com.lt.qjoke.adapter.AmusePagerAdapter;
import com.lt.qjoke.base.BaseActivity;
import com.lt.qjoke.databinding.ActivityMainBinding;
import com.lt.qjoke.messenger.Messenger;
import com.lt.qjoke.retrofit.AmuseService;
import com.lt.qjoke.utils.LogUtils;
import com.lt.qjoke.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MenuItem mSearchMenuItem;
    private SearchView mSearchView;
    private MainViewModel mMainViewModel;


    private void initViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        // 解决切换fragment导致数据重叠问题
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new AmusePagerAdapter(getSupportFragmentManager(),new String[]{"趣图","段子","视频","声音"},new String[]{AmuseService.Amuse.TYPE_IMAGE, AmuseService.Amuse.TYPE_EPISODE, AmuseService.Amuse.TYPE_VIDEO, AmuseService.Amuse.TYPE_VOICE}));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    protected void initToolbar() {
        super.initToolbar();
        getToolbar().setNavigationIcon(R.mipmap.ic_study);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainViewModel = new MainViewModel(this);
        activityMainBinding.setViewModel(mMainViewModel);
        initViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        mSearchMenuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(mSearchMenuItem);
        mSearchView.setQueryHint("输入标题...");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                LogUtils.d("onQueryTextSubmit",query+"");
                if(TextUtils.isEmpty(query)){
                    return false;
                }
                mMainViewModel.queryAmuse(query);
//                mMainPresenter.getAmuse(query);
                mSearchView.clearFocus(); // 收起键盘
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                LogUtils.d("onQueryTextChange",newText+"");
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                startActivity(new Intent(this,SettingActivity.class));
                break;
            case R.id.action_about:
                startActivity(new Intent(this,AboutActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Messenger.getDefault().unregister(this);
    }
}
