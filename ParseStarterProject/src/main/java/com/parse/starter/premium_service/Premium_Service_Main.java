package com.parse.starter.premium_service;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.Custom_List;
import com.parse.starter.R;

import java.util.List;

public class Premium_Service_Main extends AppCompatActivity {

    private ListView listView;
    private String[] title;
    private String[] active;
    private String[] small_desc;
    private String[] cost;
    private Bitmap[] images;
    private int index;
    private Bitmap temp_img;
    //private Button upload; /*****Not Required, OnClick Method called in XML*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_service_main);

        listView = (ListView) findViewById(R.id.food_box_list);

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Premium_Service");
        query.orderByDescending("createdAt");

        index = 0;
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                title = new String[objects.size()];
                active = new String[objects.size()];
                small_desc = new String[objects.size()];
                cost = new String[objects.size()];
                images = new Bitmap[objects.size()];




                for (ParseObject object : objects) {
                    if(index == objects.size())
                        break;
                    title[index] = object.get("title").toString();
                    small_desc[index] = object.get("small_description").toString();
                    active[index] = "Non - Active User";
                    if(Integer.parseInt(object.get("active").toString()) == 1)
                        active[index]  = "Active User";
                    cost[index] = "Cost: Rs." + object.get("cost").toString() + "/-";


                    /******Get Image********/
                    String img = "img";
                    temp_img = BitmapFactory.decodeResource(getResources(),R.drawable.image_default);
                    for(int k = 1;k<=4;++k) {

                        ParseFile parsefile = (ParseFile) object.get(img+k);
                        if (parsefile != null){
                            //Log.i("Kinion",img);
                            parsefile.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if(e == null){
                                        temp_img = BitmapFactory.decodeByteArray(data,0,data.length);
                                        Log.i("Kinion","Went IN");
                                    }
                                }
                            });
                            break;
                        }
                    }
                    /*******Get Image******/
                    images[index] = temp_img;
                    ++index;
                }
                Custom_List customList = new Custom_List(Premium_Service_Main.this, title, small_desc, active, cost,images);
                listView.setAdapter(customList);
            }
        });
    }

    /*****Upload*********/
    public void up_it(View arg0){
        Intent i = new Intent(getBaseContext(),Upload_New_Item.class);
        startActivity(i);
    }
    /*******************/
}
