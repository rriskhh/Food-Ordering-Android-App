package com.parse.starter.options;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.parse.starter.R;
import com.parse.starter.food_box.Food_Box_Main;
import com.parse.starter.premium_service.Premium_Service_Main;
import com.parse.starter.tiffin_service.Tiffin_Service_Main;

public class Selection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        Intent i = getIntent();
        int option = i.getIntExtra("option",0);
        if(option == 3){
            i = new Intent(getApplicationContext(), Premium_Service_Main.class);
        }
        if(option == 2){
            i = new Intent(getApplicationContext(), Food_Box_Main.class);
        }else if(option == 1)
            i = new Intent(getApplicationContext(),Tiffin_Service_Main.class);
        startActivity(i);
    }
}
