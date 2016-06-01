package com.parse.starter.options;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.parse.starter.R;
import com.parse.starter.UserList;
import com.parse.starter.food_box.Food_Box_Main;
import com.parse.starter.premium_service.Premium_Service_Main;
import com.parse.starter.tiffin_service.Selection_Tiffin;
import com.parse.starter.tiffin_service.Tiffin_Service_Main;

public class MyFragment extends Fragment {

    private String background[] = {"Tiffin","Box","Premium"};
    private int cur_page;
    private Intent i;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cur_page = getArguments().getInt("page", 0);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){

        View v = inflater.inflate(R.layout.activity_my_fragment,container,false);
        ImageView iv = (ImageView) v.findViewById(R.id.iv);
        ImageView iv2 = (ImageView) v.findViewById(R.id.iv2);
        //TextView tv = (TextView) v.findViewById(R.id.tv);
        switch (cur_page) {

            case 2 : iv.setBackgroundResource(R.drawable.box);
                iv2.setBackgroundResource(R.drawable.box2);
                //tv.setText("Food Box");
                break;
            case 3: iv.setBackgroundResource(R.drawable.premium);
                    iv2.setBackgroundResource(R.drawable.premium2);
                    //tv.setText("Premium Service");
                break;
            default:iv.setBackgroundResource(R.drawable.tiffin);
                iv2.setBackgroundResource(R.drawable.tiffin2);
                //tv.setText();
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Option_Menu.context, Selection.class);
                //i.putExtra("option", cur_page);
                if(cur_page == 1)
                    i = new Intent(Option_Menu.context, Tiffin_Service_Main.class);
                if(cur_page == 2)
                    i = new Intent(Option_Menu.context, Food_Box_Main.class);
                if(cur_page == 3)
                    i = new Intent(Option_Menu.context, Premium_Service_Main.class);

                startActivity(i);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Option_Menu.context, Selection_Tiffin.class);
                //i.putExtra("option",cur_page);
                if(cur_page == 1)
                    i = new Intent(Option_Menu.context, Tiffin_Service_Main.class);
                if(cur_page == 2)
                    i = new Intent(Option_Menu.context, Food_Box_Main.class);
                if(cur_page == 3)
                    i = new Intent(Option_Menu.context, Premium_Service_Main.class);

                startActivity(i);
            }
        });

        return v;
    }
}
