package com.lt.qjoke;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lt.qjoke.adapter.AmusePagerAdapter;
import com.lt.qjoke.retrofit.AmuseService;
import com.lt.qjoke.utils.LogUtils;
import com.lt.qjoke.viewmodel.MainViewModel;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MenuItem mSearchMenuItem;
    private SearchView mSearchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        activityMainBinding.setVariable(BR.viewModel,new MainViewModel());
        initToolbar();
        initViewPager();
    }

    private void initViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        // 解决切换fragment导致数据重叠问题
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new AmusePagerAdapter(getSupportFragmentManager(),new String[]{"趣图","段子","视频","声音"},new String[]{AmuseService.Amuse.TYPE_IMAGE, AmuseService.Amuse.TYPE_EPISODE, AmuseService.Amuse.TYPE_VIDEO, AmuseService.Amuse.TYPE_VOICE}));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setNavigationIcon(R.mipmap.ic_study);
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
//                startActivity(new Intent(this,SettingActivity.class));
                break;
            case R.id.action_about:
//                startActivity(new Intent(this,AboutActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
