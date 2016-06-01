package com.parse.starter.tiffin_service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.parse.ParseUser;
import com.parse.starter.Login_Service;
import com.parse.starter.R;
import com.parse.starter.account.Account_Main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tiffin_Service_Main extends AppCompatActivity {

    final static String BREAKFAST = "10:00 ";
    final static String LUNCH = "14:00 ";
    final static String DINNER = "20:00 ";
    static Context context;
    private ImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiffin_service_main);

        profile = (ImageView) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i;
                Log.i("Kinion", ParseUser.getCurrentUser().toString());
                if (ParseUser.getCurrentUser().getUsername().toString().equals("guestUser141njnUY823B8Bbbu"))
                    i = new Intent(getApplicationContext(), Login_Service.class);
                else
                    i = new Intent(getApplicationContext(), Account_Main.class);

                startActivity(i);
            }
        });

        context = getApplicationContext();
        ( (ViewPager)findViewById(R.id.pager_options) ).setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
    }
}


/*********TIME DIFFERENCE
 String time2 =  DateFormat.getTimeInstance().format(new Date());
 try{

 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
 Date d1 = simpleDateFormat.parse("05:02 PM");
 Date d2 = simpleDateFormat.parse(time2);

 long difference = d2.getTime() - d1.getTime();
 Log.i("Kinion",difference+"");


 }catch(ParseException e){
 e.printStackTrace();
 }
 **********/