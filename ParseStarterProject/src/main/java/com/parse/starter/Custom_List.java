package com.parse.starter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by rriskhh on 16/04/16.
 */
public class Custom_List extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] title;
    private final String[] small_desc;
    private final String[] serves;
    private final String[] cost;
    private final Bitmap[] images;

    public Custom_List(Activity context, String[] title, String[] small_desc,String[] serves, String[] cost,Bitmap[] images){
        super(context,R.layout.list_row,title);
        this.context = context;
        this.title = title;
        this.small_desc = small_desc;
        this.serves = serves;
        this.cost = cost;
        this.images = images;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.list_row, null, true);

        TextView title_tv = (TextView) rowView.findViewById(R.id.title_box);
        TextView small_d_tv = (TextView) rowView.findViewById(R.id.small_desc);
        TextView serves_tv = (TextView) rowView.findViewById(R.id.serves);
        TextView cost_tv = (TextView) rowView.findViewById(R.id.cost);
        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);

        title_tv.setText(title[position]);
        small_d_tv.setText(small_desc[position]);
        serves_tv.setText(serves[position]);
        cost_tv.setText(cost[position]);
        Log.i("Kinion",images[position]+"");
        icon.setImageBitmap(images[position]);
        //icon.setBackground(new BitmapDrawable(context.getResources(),images[position]));

        return rowView;
    }
}
