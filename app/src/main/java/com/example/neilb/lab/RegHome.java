package com.example.neilb.lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.RealmResults;

@EActivity(R.layout.activity_reg_home)
public class RegHome extends AppCompatActivity {
    @ViewById(R.id.newUser)
    Button newUser;
    @ViewById(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bean
    UserManager uman;

    @AfterViews
    public void init(){
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        RealmResults<UserObject> userList = uman.getUserList();
        UserAdapter ua = new UserAdapter(this, userList);
        recyclerView.setAdapter(ua);
    }

    @Click(R.id.newUser)
    public void sendToReg(){
        register_.intent(this).start();
        finish();
    }


    @Click(R.id.returnButton)
    public void returnToLogin(){
        login_.intent(this).start();
    }

    public void edit(UserObject u){
        String name = u.getUsername();
        finish();
        EditUser_.intent(this)
                .username(name)
                .start();
    }

    public void delete(UserObject u){
        uman.deletDis(u);
    }
}
