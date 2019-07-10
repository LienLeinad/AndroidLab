package com.example.neilb.lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import io.realm.RealmResults;

@EActivity(R.layout.activity_welcome2)
public class welcome extends AppCompatActivity {


    @Extra
    String name;
    @ViewById(R.id.userText)
    TextView userText;
    @ViewById(R.id.recyclerView)
    RecyclerView recyclerView;


//    @Bean
//    UserManager uman;
    @AfterViews
    public void init() {

        userText.setText(name);
    }

}
