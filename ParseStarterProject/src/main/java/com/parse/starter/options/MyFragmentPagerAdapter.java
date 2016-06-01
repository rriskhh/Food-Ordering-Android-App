package com.parse.starter.options;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by rriskhh on 14/04/16.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter{

    final int PAGE_COUNT = 3;
    public MyFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0){

        MyFragment fragment = new MyFragment();
        Bundle data = new Bundle();
        data.putInt("page", arg0 + 1);
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public int getCount(){
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position){
        if(position == 2)
            return "Food Xpress";
        if(position == 1)
            return "Food Box";
        return "Tiffin Service";
    }

}
