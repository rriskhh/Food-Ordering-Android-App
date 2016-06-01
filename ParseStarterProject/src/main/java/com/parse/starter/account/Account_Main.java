package com.parse.starter.account;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;

public class Account_Main extends AppCompatActivity {

    private EditText edit_mob;
    private EditText edit_mail;
    private EditText edit_pwd;
    private ArrayList<String> array;
    private EditText edit_add;
    private TextView add_add;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_main);

        edit_mob = (EditText)findViewById(R.id.edit_mob);
        edit_mail = (EditText) findViewById(R.id.edit_mail);
        edit_add = (EditText) findViewById(R.id.edit_add);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        add_add = (TextView) findViewById(R.id.add_add);
        linearLayout = (LinearLayout) findViewById(R.id.account_layout);

        edit_mob.setText(ParseUser.getCurrentUser().getUsername().toString());
        edit_mail.setText(ParseUser.getCurrentUser().getEmail().toString());

        //Log.i("Kinion", ParseUser.getCurrentUser().getUsername().toString());

        array = new ArrayList<String>();
        fill_add();



        /*******Change Password********/
        edit_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setEnabled(false);
                LayoutInflater layoutInflater =LayoutInflater.from(getBaseContext());
                final View new_pwd_popup = layoutInflater.inflate(R.layout.activity_password_popup,null);
                final PopupWindow popupWindow = new PopupWindow();
                popupWindow.setContentView(new_pwd_popup);
                popupWindow.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);

                Button cancel = (Button) new_pwd_popup.findViewById(R.id.cancel_pwd);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linearLayout.setEnabled(true);
                        popupWindow.dismiss();
                    }
                });

                Button change = (Button) new_pwd_popup.findViewById(R.id.change);
                change.setOnClickListener(new View.OnClickListener() {
                    EditText curr_pwd, new_pwd, re_new_pwd;
                    @Override
                    public void onClick(View v) {

                        curr_pwd = (EditText) new_pwd_popup.findViewById(R.id.current_pwd);
                        new_pwd = (EditText) new_pwd_popup.findViewById(R.id.new_pwd);
                        re_new_pwd = (EditText) new_pwd_popup.findViewById(R.id.re_new_pwd);
                        if(curr_pwd.getText().toString().isEmpty())
                            Toast.makeText(getBaseContext(),"Current Password not Entered",Toast.LENGTH_LONG).show();
                        else if(!new_pwd.getText().toString().equals(re_new_pwd.getText().toString()))
                            Toast.makeText(getBaseContext(),"Passwords do not match",Toast.LENGTH_LONG).show();
                        else{
                            ParseUser.logInInBackground(ParseUser.getCurrentUser().getUsername(), curr_pwd.getText().toString(), new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if(user != null){
                                        ParseUser.getCurrentUser().setPassword(new_pwd.getText().toString());
                                        Toast.makeText(getBaseContext(),"Password Successfully Changed",Toast.LENGTH_LONG).show();
                                        linearLayout.setEnabled(true);
                                        popupWindow.dismiss();
                                    }else{
                                        Toast.makeText(getBaseContext(),"Incorrect Password",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
            }
        });
        /*******Change Password********/

        /**********Add New Address*******/
        add_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setEnabled(false);
                LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
                final View new_add_popup = layoutInflater.inflate(R.layout.acitvity_address_popup, null);
                final PopupWindow popupWindow = new PopupWindow();
                popupWindow.setContentView(new_add_popup);
                popupWindow.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);

                Button cancel = (Button) new_add_popup.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linearLayout.setEnabled(true);
                        popupWindow.dismiss();
                    }
                });

                Button add = (Button) new_add_popup.findViewById(R.id.add);
                add.setOnClickListener(new View.OnClickListener() {

                    EditText line1, line2, line3, pincode;

                    @Override
                    public void onClick(View v) {
                        line1 = (EditText) new_add_popup.findViewById(R.id.line_1);
                        line2 = (EditText) new_add_popup.findViewById(R.id.line_2);
                        line3 = (EditText) new_add_popup.findViewById(R.id.line_3);
                        pincode = (EditText) new_add_popup.findViewById(R.id.pincode);
                        if (line1.getText().toString().isEmpty() ||
                                pincode.getText().toString().isEmpty())
                            Toast.makeText(getApplicationContext(), "Please complete your address", Toast.LENGTH_LONG).show();
                        else {
                            ParseObject object = new ParseObject("Address");
                            object.put("username", ParseUser.getCurrentUser().getUsername().toString());
                            object.put("line_1", line1.getText().toString());
                            if(!line2.getText().toString().isEmpty())
                                object.put("line_2", line2.getText().toString());
                            else object.put("line_2"," ");
                            if(!line2.getText().toString().isEmpty())
                                object.put("line_3", line3.getText().toString());
                            else object.put("line_3"," ");
                            object.put("pincode", pincode.getText().toString());
                            object.saveInBackground();
                            Toast.makeText(getApplicationContext(), "Address Successfully Added", Toast.LENGTH_LONG).show();
                            linearLayout.setEnabled(true);
                            popupWindow.dismiss();
                            Intent i = getIntent();
                            finish();
                            startActivity(i);
                        }
                    }
                });
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
            }
        });
        /***********Add New Address******/
    }

    /******Display Address********/
    void fill_add(){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Address");
        query.whereEqualTo("username", edit_mob.getText().toString());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                array.clear();
                if (objects.size() == 0) {
                    array.add("No address added");
                } else {
                    for (ParseObject object : objects) {
                        array.add(String.valueOf(object.get("line_1"))
                                + "\n" + String.valueOf(object.get("line_2"))
                                + "\n" + String.valueOf(object.get("line_3"))
                                + "- " + String.valueOf(object.get("pincode")));

                        //Log.i("Kinion", array.get(array.size() - 1).toString());
                    }
                }
                String s = array.get(0);
                for(int i = 1;i<array.size();++i)
                    s += "\n\n" + array.get(i);
                edit_add.setText(s);
            }
        });
    }
    /*******************/
}
