package com.parse.starter.options;

import android.app.ActionBar;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.starter.Login_Service;
import com.parse.starter.R;
import com.parse.starter.account.Account_Main;

public class Option_Menu extends AppCompatActivity {

    static Context context;
    private ImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option__menu);

        profile = (ImageView) findViewById(R.id.profile);
        /*profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i;
                Log.i("Kinion", ParseUser.getCurrentUser().toString());
                if(ParseUser.getCurrentUser().getUsername().toString().equals("guestUser141njnUY823B8Bbbu"))
                    i = new Intent(getApplicationContext(), Login_Service.class);
                else
                    i = new Intent(getApplicationContext(), Account_Main.class);

                startActivity(i);
            }
        });*/

        context = getApplicationContext();
        ( (ViewPager)findViewById(R.id.pager_options) ).setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
    }

    public void send_to_account(View arg0){
        Intent i;
        Log.i("Kinion", ParseUser.getCurrentUser().toString());
        if(ParseUser.getCurrentUser().getUsername().toString().equals("guestUser141njnUY823B8Bbbu"))
            i = new Intent(getApplicationContext(), Login_Service.class);
        else
            i = new Intent(getApplicationContext(), Account_Main.class);

        startActivity(i);
    }


    @Override
    public void onBackPressed(){

        Toast.makeText(getBaseContext(),"Exiting",Toast.LENGTH_LONG).show();
        finish();
    }
}
