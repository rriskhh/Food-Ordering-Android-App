package com.parse.starter.tiffin_service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.Custom_List;
import com.parse.starter.R;

import java.util.List;

public class Dinner_Main extends AppCompatActivity {

    private ListView listView;
    private String[] title;
    private String[] serves;
    private String[] small_desc;
    private String[] cost;
    private Bitmap[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner__main);

        listView = (ListView) findViewById(R.id.food_box_list);

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Tiffin");
        query.whereEqualTo("type", "Dinner");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                title = new String[objects.size()];
                serves = new String[objects.size()];
                small_desc = new String[objects.size()];
                cost = new String[objects.size()];
                images = new Bitmap[objects.size()];


                int index = 0;
                images[index] = BitmapFactory.decodeResource(getResources(), R.drawable.image_default);

                for (ParseObject object : objects) {
                    title[index] = object.get("title").toString();
                    small_desc[index] = object.get("small_description").toString();
                    serves[index] = "Serves " + object.get("serves").toString() + " people.";
                    cost[index] = "Cost: Rs." + object.get("cost").toString() + "/-";
                    ++index;
                }
                Custom_List customList = new Custom_List(Dinner_Main.this, title, small_desc, serves, cost,images);
                listView.setAdapter(customList);
            }
        });
    }
}
