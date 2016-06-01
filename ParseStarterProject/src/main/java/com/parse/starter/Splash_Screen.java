package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.os.Handler;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.starter.options.Option_Menu;

public class Splash_Screen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    int status = 0;
    private Handler mHandler = new Handler();
    private ProgressBar pb;
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        /*
            ************SET NON-ACTION BAR ACTIVITY*********
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        */
        setContentView(R.layout.activity_splash__screen);
        pb = (ProgressBar)findViewById(R.id.progressBar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(status < 300){
                    status += 1;
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        pb.setProgress(status);

                    }
                });
            }
        }).start();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(((ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE))
                        .getActiveNetworkInfo() != null ) {

                    if(ParseUser.getCurrentUser() != null){
                        Intent i = new Intent(getApplicationContext(), Option_Menu.class);
                        startActivity(i);
                    }else {
                        Intent i = new Intent(getApplicationContext(), Login_Service.class);
                        startActivity(i);
                    }
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please check your connection",Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    },50);
                }
                //getParent().finish();
                //moveTaskToBack(true);
            }
        }, SPLASH_TIME_OUT);
    }
}

