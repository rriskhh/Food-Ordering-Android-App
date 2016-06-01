package com.parse.starter.tiffin_service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.starter.R;

public class Selection_Tiffin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_tiffin);
        Intent i = getIntent();
        int option = i.getIntExtra("option",0);
        if(option == 3){
            i = new Intent(getApplicationContext(), Dinner_Main.class);
        }
        if(option == 2){
            i = new Intent(getApplicationContext(), Lunch_Main.class);
        }else if(option == 1)
            i = new Intent(getApplicationContext(),Breakfast_Main.class);
        startActivity(i);
    }
}
