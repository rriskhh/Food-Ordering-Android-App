/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

  EditText usernameField;
  EditText passwordField;

  TextView changeSignUpModeTextView;

  Boolean signUpModeActive;

  Button signUpButton;

  ImageView logo;
  RelativeLayout relativeLayout;

  /*******LogIn on Enter Press*********/
  @Override
  public boolean onKey(View v,int keyCode,KeyEvent event){

    if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
      signUpOrLogin(v);
    }
    return false;
  }

  /******LogIn TextView*******/
  @Override
  public void onClick(View v){

    if(v.getId() == R.id.changeSignUpMode){

      Log.i("Appinfo", "Change SignUp Mode");

      if(signUpModeActive == true){

        signUpModeActive = false;
        changeSignUpModeTextView.setText("Sign Up");
        signUpButton.setText("Log In");

      }else{
        signUpModeActive = true;
        changeSignUpModeTextView.setText("Log In");
        signUpButton.setText("Sign Up");
      }

      /********Hide Keyboard**********/
    }else if(v.getId() == R.id.logo || v.getId() == R.id.relativeLayout){

      InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

    }

  }

  /********SignUp Button*********/
  public void signUpOrLogin(View view){

    //Log.i("Appinfo",String.valueOf(usernameField.getText()));
    //Log.i("Appinfo", String.valueOf(passwordField.getText()));

    if(signUpModeActive == true) {
      ParseUser user = new ParseUser();
      user.setUsername(String.valueOf(usernameField.getText()));
      user.setPassword(String.valueOf(passwordField.getText()));

      user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {

          if (e == null) {

            Log.i("Appinfo", "Signup Successful");
            showUserList();

          } else {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
          }
        }
      });
    }else {
      ParseUser.logInInBackground(String.valueOf(usernameField.getText()), String.valueOf(passwordField.getText()), new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {
          if(user != null){
            Log.i("Appinfo","Login Successful");
            showUserList();
          }else{
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
          }
        }
      });
    }
  }


  public void showUserList(){
    Intent i = new Intent(getApplicationContext(),UserList.class);
    startActivity(i);
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if(ParseUser.getCurrentUser() != null){
      showUserList();
    }

    signUpModeActive = true;

    usernameField = (EditText)findViewById(R.id.username);
    passwordField = (EditText) findViewById(R.id.password);
    signUpButton = (Button) findViewById(R.id.signUpButton);
    changeSignUpModeTextView = (TextView) findViewById(R.id.changeSignUpMode);
    logo = (ImageView) findViewById(R.id.logo);
    relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

    changeSignUpModeTextView.setOnClickListener(this);
    logo.setOnClickListener(this);
    relativeLayout.setOnClickListener(this);

    usernameField.setOnKeyListener(this);
    passwordField.setOnKeyListener(this);


    /*******New Parse User*******/

    /*ParseUser user = new ParseUser();
    user.setUsername("krrishmittal");
    user.setPassword("anyPass");
    user.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {

        if(e == null){
          Log.i("signUp", "Successful");
        }else {
          Log.i("signUp", "Failed");
        }
      }
    });*/

    /*******Log in existing User*******/

    /*ParseUser.logInInBackground("krrishmittal", "anyPass1", new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {

        if(user != null){
          Log.i("logIn","Successful");
        }else {
          Log.i("logIn","Failed");
        }
      }
    });*/

    //ParseUser.logOut();

    /*******Check LogIn Rqd or Not*******/

    /*if (ParseUser.getCurrentUser() != null){
      Log.i("currentUser", "User Logged In");
    }else{
      Log.i("currentUser","User Not Logged In");
    }*/

    /*******New Parse Object*******/

    /*ParseObject score = new ParseObject("Score");
    score.put("playerName", "rob");
    score.put("score", 96);
    score.saveInBackground();*/

    /*******New Parse Object*2******/

    /*ParseObject gameScore = new ParseObject("GameScore");
    gameScore.put("score", 67);
    gameScore.put("playerName", "kirsten");
    gameScore.put("cheatMode", false);
    gameScore.saveInBackground(new SaveCallback() {
      public void done(ParseException e) {
        if (e == null) {
          Log.i("Parse", "Save Succeeded");
        } else {
          Log.i("Parse", "Save Failed");
        }
      }
    });*/

    ParseAnalytics.trackAppOpenedInBackground(getIntent());

    //ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");

    /*******Modify Parse Object*******/

    /*query.getInBackground("xM1dHyxnYw", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {

        if(e == null){

          object.put("score",400);
          object.saveInBackground();

        }
      }
    });*/

    /*******Display Specific Parse Object Detail**Select type Command*****/

    /*
    //query.whereEqualTo("playerName","Devika");
    //query.setLimit(1);
    query.findInBackground(new FindCallback<ParseObject>() {
      @Override
      public void done(List<ParseObject> objects, ParseException e) {

        if(e == null){
          Log.i("findInBackground", "Retrieved " + objects.size() + " objects");
          for(ParseObject object : objects){
            Log.i("findInBackground", String.valueOf(object.get("playerName")));
          }
        }
      }
    });*/
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

}
