package com.parse.starter.premium_service;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.ParseACL;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.starter.R;
import com.parse.starter.options.Option_Menu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Upload_New_Item extends AppCompatActivity {

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private Bitmap bitmap1 = null;
    private Bitmap bitmap2 = null;
    private Bitmap bitmap3 = null;
    private Bitmap bitmap4 = null;
    private EditText it_name;
    private EditText it_tag;
    private EditText it_desciption;
    private EditText it_rate;
    private EditText it_place;
    private EditText phone;
    private RadioGroup deliver;
    private int dummy;
    private int it_deliver = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__new__item);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);

        phone = (EditText) findViewById(R.id.mob);
        it_name = (EditText) findViewById(R.id.item_name);
        it_tag = (EditText) findViewById(R.id.tag);
        it_desciption = (EditText) findViewById(R.id.description);
        it_rate = (EditText) findViewById(R.id.rate);
        it_place = (EditText) findViewById(R.id.place);
        deliver = (RadioGroup) findViewById(R.id.deliver);
        deliver.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton_yes)
                    it_deliver = 1;
                else
                    it_deliver = 0;
            }
        });
    }

    public void add_Image(View arg0){

        if(arg0.getId() == R.id.img1)
            dummy = 1;
        if(arg0.getId() == R.id.img2)
            dummy = 2;
        if(arg0.getId() == R.id.img3)
            dummy = 3;
        if(arg0.getId() == R.id.img4)
            dummy = 4;

        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,dummy);

    }

    /*********Display Added Image*********/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data!=null){

            Uri selectedImage = data.getData();

            try {
                Bitmap bitmapImage = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(),selectedImage);

                if(requestCode == 1) {
                    bitmap1 = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(),selectedImage);
                    img1.setBackgroundColor(getResources().getColor(R.color.theme_white));
                    //img1.setImageBitmap(bitmap1);
                    img1.setBackground(new BitmapDrawable(getResources(),bitmap1));
                }
                if(requestCode == 2) {
                    bitmap2 = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(),selectedImage);
                    img2.setBackgroundColor(getResources().getColor(R.color.theme_white));
                    //img2.setImageBitmap(bitmap2);
                    img2.setBackground(new BitmapDrawable(getResources(), bitmap2));
                }
                if(requestCode == 3) {
                    bitmap3 = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(),selectedImage);
                    img3.setBackgroundColor(getResources().getColor(R.color.theme_white));
                    //img3.setImageBitmap(bitmap3);
                    img3.setBackground(new BitmapDrawable(getResources(),bitmap3));
                }
                if(requestCode == 4) {
                    bitmap4 = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(),selectedImage);
                    img4.setBackgroundColor(getResources().getColor(R.color.theme_white));
                    //img4.setImageBitmap(bitmap4);
                    img4.setBackground(new BitmapDrawable(getResources(),bitmap4));
                }
            } catch (IOException e) {
                Toast.makeText(getBaseContext(),"Erro Loading Image.",Toast.LENGTH_LONG).show();
            }
        }
    }
    /*********************/

    public void add_New_Item(View arg0){
        if(phone.getText().toString().isEmpty() ||
                it_name.getText().toString().isEmpty() ||
                it_desciption.getText().toString().isEmpty() ||
                it_rate.getText().toString().isEmpty() ||
                it_place.getText().toString().isEmpty())
            Toast.makeText(getBaseContext(),"*Required field empty",Toast.LENGTH_LONG).show();
        else{
            ParseObject object = new ParseObject("Premium_Service");
            object.put("username",phone.getText().toString());
            object.put("title",it_name.getText().toString());
            object.put("cost",it_rate.getText().toString());
            object.put("large_description",it_desciption.getText().toString());
            object.put("place",it_place.getText().toString());
            object.put("deliver",it_deliver+"");
            object.put("active","0");
            if(it_tag.getText().toString().isEmpty())
                object.put("small_description","No information given.");
            else
                object.put("small_description",it_tag.getText().toString());

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] byteArray;
            ParseFile parseFile;
            ParseACL parseACL = new ParseACL();
            parseACL.setPublicReadAccess(true);

            if(bitmap1 != null){
                bitmap1.compress(Bitmap.CompressFormat.PNG,100,stream);
                byteArray = stream.toByteArray();
                parseFile = new ParseFile(phone.getText().toString()+"_img1.png",byteArray);
                object.put("img1",parseFile);
            }

            if(bitmap2 != null){
                bitmap1.compress(Bitmap.CompressFormat.PNG,100,stream);
                byteArray = stream.toByteArray();
                parseFile = new ParseFile(phone.getText().toString()+"_img2.png",byteArray);
                object.put("img2",parseFile);
            }

            if(bitmap3 != null){
                bitmap3.compress(Bitmap.CompressFormat.PNG,100,stream);
                byteArray = stream.toByteArray();
                parseFile = new ParseFile(phone.getText().toString()+"_img3.png",byteArray);
                object.put("img3",parseFile);
            }

            if(bitmap4 != null){
                bitmap4.compress(Bitmap.CompressFormat.PNG,100,stream);
                byteArray = stream.toByteArray();
                parseFile = new ParseFile(phone.getText().toString()+"_img4.png",byteArray);
                object.put("img4",parseFile);
            }

            object.setACL(parseACL);
            object.saveInBackground();
            Toast.makeText(getBaseContext(),"Thanks. Our team will contact you soon.",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getBaseContext(),Option_Menu.class));
            finish();
        }
    }
}
