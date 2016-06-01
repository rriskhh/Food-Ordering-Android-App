package com.parse.starter.tiffin_service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.parse.starter.R;

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

            case 2 : iv.setBackgroundResource(R.drawable.lunch1);
                iv2.setBackgroundResource(R.drawable.lunch2);
                //tv.setText("Food Box");
                break;
            case 3: iv.setBackgroundResource(R.drawable.dinner1);
                    iv2.setBackgroundResource(R.drawable.dinner2);
                    //tv.setText("Premium Service");
                break;
            default:iv.setBackgroundResource(R.drawable.breakfast1);
                iv2.setBackgroundResource(R.drawable.breakfast2);
                //tv.setText();
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Tiffin_Service_Main.context, Selection_Tiffin.class);
                //i.putExtra("option",cur_page);
                if(cur_page == 1)
                    i = new Intent(Tiffin_Service_Main.context,Breakfast_Main.class);
                if(cur_page == 2)
                    i = new Intent(Tiffin_Service_Main.context,Lunch_Main.class);
                if(cur_page == 3)
                    i = new Intent(Tiffin_Service_Main.context,Dinner_Main.class);

                startActivity(i);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Tiffin_Service_Main.context, Selection_Tiffin.class);
                //i.putExtra("option",cur_page);
                if(cur_page == 1)
                    i = new Intent(Tiffin_Service_Main.context,Breakfast_Main.class);
                if(cur_page == 2)
                    i = new Intent(Tiffin_Service_Main.context,Lunch_Main.class);
                if(cur_page == 3)
                    i = new Intent(Tiffin_Service_Main.context,Dinner_Main.class);
                startActivity(i);
            }
        });

        return v;
    }
}
