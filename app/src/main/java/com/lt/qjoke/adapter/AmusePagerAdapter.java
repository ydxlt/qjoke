package com.lt.qjoke.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lt.qjoke.ui.AmuseFragment;

/**
 * Created by luotong on 2017/9/20.
 */

public class AmusePagerAdapter extends FragmentPagerAdapter {

    private String[] mPageTitles;
    private String[] mType;

    public AmusePagerAdapter(FragmentManager fm, String[] pageTitles, String[] type) {
        super(fm);
        mPageTitles = pageTitles;
        mType = type;
    }

    @Override
    public Fragment getItem(int position) {
        return AmuseFragment.newInstance(mType[position]);
    }

    @Override
    public int getCount() {
        return mPageTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitles[position];
    }
}
