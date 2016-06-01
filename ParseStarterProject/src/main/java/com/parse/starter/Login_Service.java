package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.options.Option_Menu;

import org.w3c.dom.Text;

import java.util.List;

public class Login_Service extends AppCompatActivity implements View.OnKeyListener,View.OnClickListener{

    private Switch changeMode;
    private EditText mail;
    private EditText pwd;
    private EditText phone;
    private boolean modeActive = true;
    private View view;
    private Button startButton;
    private RelativeLayout relativeLayout;
    private ImageView logo;
    private TextView skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__service);

        view = this.findViewById(R.id.relativeLayout).getRootView();
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        logo = (ImageView) findViewById(R.id.logo);
        changeMode = (Switch)findViewById(R.id.selection);
        phone = (EditText) findViewById(R.id.phone);
        mail = (EditText) findViewById(R.id.mail);
        pwd = (EditText) findViewById(R.id.pwd);
        startButton = (Button) findViewById(R.id.startButton);
        skip = (TextView) findViewById(R.id.skip);

        changeModeFunction();
        logo.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        /****Skip Option******/
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground("guestUser141njnUY823B8Bbbu", "dewfw278TBUBUcbeq78", new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!= null){
                            Toast.makeText(getApplicationContext(),"Welcome, Guest User",Toast.LENGTH_LONG).show();
                            launch_new_activity();
                        }else
                            Toast.makeText(getApplicationContext(),"Please check your Connection",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    /****Close Keyborad*********/
    @Override
    public void onClick(View v){
        if(view.getId() == R.id.logo || view.getId() == R.id.relativeLayout){
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }
    /****************************/

    /************Change Mode*************/
    private void changeModeFunction(){
        changeMode.setChecked(false);
        changeMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.findViewById(R.id.pwd).getLayoutParams();
                if (isChecked) {
                    mail.setVisibility(View.VISIBLE);
                    modeActive = false;
                    mail.setText("");
                    phone.setText("");
                    pwd.setText("");
                    lp.addRule(RelativeLayout.BELOW, R.id.mail);
                }
                if (!isChecked) {
                    mail.setVisibility(EditText.GONE);
                    modeActive = true;
                    mail.setText("");
                    phone.setText("");
                    pwd.setText("");
                    lp.addRule(RelativeLayout.BELOW, R.id.phone);
                }
                pwd.setLayoutParams(lp);
            }
        });
    }
    /***********************************/

    /**************Log In****************/
    public void logOrSign(View view){

        Log.i("Kinion",String.valueOf(modeActive));
        if(modeActive){
            logIn();
        }else if(!modeActive) {
            signUp();
        }
    }
    /*****************************/

    /************Sign Up**********/
    void signUp(){

        if (String.valueOf(mail.getText()).trim().isEmpty() || String.valueOf(pwd.getText()).trim().isEmpty() || String.valueOf(phone.getText()).trim().isEmpty())
            Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_LONG).show();
        else {
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("User");
            query.whereEqualTo("username", String.valueOf(phone.getText()).trim());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null && objects.size() == 0)
                        saveUser();
                    else
                        Toast.makeText(getApplicationContext(),"Error! Try again later",Toast.LENGTH_LONG).show();
                }
            });


        }
    }
    /**********************/

    /******Save User*******/
    void saveUser(){
        ParseUser user = new ParseUser();
        user.setUsername(String.valueOf(phone.getText()).trim());
        user.setEmail(String.valueOf(mail.getText()).trim());
        user.setPassword(String.valueOf(pwd.getText()).trim());
        //user.put("mobile", Integer.parseInt(String.valueOf(phone.getText()).trim()));

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("Kinion ", "SignUp Successful");
                    Toast.makeText(getApplicationContext(), "Please Verify your Number", Toast.LENGTH_LONG).show();
                    launch_new_activity();
                } else {
                    Toast.makeText(getApplicationContext(),"Error! Try again later",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    /*********************/

    /*********Log In*********/
    void logIn(){

        if ( String.valueOf(pwd.getText()).trim().isEmpty() || String.valueOf(phone.getText()).trim().isEmpty())
            Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_LONG).show();
        else{

            ParseUser.logInInBackground(String.valueOf(phone.getText()).trim(), String.valueOf(pwd.getText()).trim(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(user!= null){
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                        launch_new_activity();
                    }else
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    /***********************/

    /******Enter Press*******/
    @Override
    public boolean onKey(View v,int keyCode,KeyEvent event){

        if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
            logOrSign(v);
        }
        return false;
    }

    /********New Activity*******/
    private void launch_new_activity(){
        Intent i = new Intent(getApplicationContext(),Option_Menu.class);
        startActivity(i);
        finish();
    }
    /*************************/



}
