package com.parse.starter.tiffin_service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.parse.starter.options.Option_Menu;

import java.net.PasswordAuthentication;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by rriskhh on 14/04/16.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter{

    final int PAGE_COUNT = 3;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("kk:mm");

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
    public CharSequence getPageTitle(int position) {

        try {
            Date date2 = simpleDateFormat.parse(Tiffin_Service_Main.BREAKFAST);
            if (position == 2) {
                date2 = simpleDateFormat.parse(Tiffin_Service_Main.DINNER);
                return "Dinner" + getDate(date2);
            }
            if (position == 1) {
                date2 = simpleDateFormat.parse(Tiffin_Service_Main.LUNCH);
                return "Lunch" + getDate(date2);
            }
            return "Breakfast" + getDate(date2);
        }catch(ParseException e){
            return "Tiffin_Option";
        }
    }

    private String getDate(Date d1) throws ParseException{

        Date d2 = simpleDateFormat.parse(DateFormat.getTimeInstance().format(new Date()));
        Log.i("Kinion",System.currentTimeMillis()+"");
        long difference = d2.getTime() - d1.getTime();
        if(difference > 0 && d1.getTime()>0)
            return " (Tomorrow)";
        return " (Today)";
    }
}
