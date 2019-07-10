package com.example.neilb.lab;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.androidannotations.annotations.EBean;


@EBean
public class MyPrefs {
    Gson gson = new GsonBuilder().create();
    Context context;
    SharedPreferences preferences;
    //constructor
    public MyPrefs(Context context){
        this.context = context;
        preferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }







    public void clearRememberedUser(){
        SharedPreferences.Editor edit = preferences.edit();
        edit.remove("savedUser");
        edit.apply();
    }
    public void saveRememberedUser(String name, String password){
        User u = new User();
        u.setName(name);
        u.setPassword(password);
        SharedPreferences.Editor edit = preferences.edit();
        String jsonString = gson.toJson(u);
        edit.putString("savedUser", jsonString);
        edit.apply();
    }
    public User getRememberedUser(){
        String jsonString = preferences.getString("savedUser", "this is bad");
        User u = null;
        if(!jsonString.equals("this is bad")){
            u = gson.fromJson(jsonString,User.class);
        }
        SharedPreferences.Editor edit = preferences.edit();
        edit.remove("savedUser");


        return u;
    }

}
