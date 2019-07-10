package com.example.neilb.lab;

import android.app.Application;
import io.realm.Realm;
import org.androidannotations.annotations.EApplication;

@EApplication
public class MyRealmApp extends Application {
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
    }
}
