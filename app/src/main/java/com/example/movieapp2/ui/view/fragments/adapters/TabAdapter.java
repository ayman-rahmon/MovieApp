package com.example.movieapp2.ui.view.fragments.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>() ;


    public TabAdapter(FragmentManager fm) {
        super(fm);
    }



    public void addFragment(Fragment fragment , String title ){
    mFragmentList.add(fragment);
    mFragmentTitleList.add(title);
    }


    @Override
    public Fragment getItem(int position ) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }




}
