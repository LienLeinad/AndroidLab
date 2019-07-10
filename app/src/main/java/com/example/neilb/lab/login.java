package com.example.neilb.lab;


import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class login extends AppCompatActivity{
    @ViewById(R.id.login)
    TextView login2;

    @ViewById(R.id.editName)
    EditText name;
    @ViewById (R.id.editText3)
    EditText password;

    @ViewById(R.id.LoginButton)
    Button login;

    @ViewById (R.id.RegisterButton)
    Button register;

    @ViewById(R.id.checkBox)
    CheckBox checkBox;

    @Bean
    MyPrefs pref;

    @Bean
    UserManager uman;

    @AfterViews
    public void init(){
        User u = pref.getRememberedUser();
        if(u != null){
            name.setText(u.getName());
            password.setText(u.getPassword());
        }
    }

    @Click(R.id.RegisterButton)
    public void sendToReg(){
        finish();
        RegHome_.intent(this).start();
    }

    @Click(R.id.LoginButton)
    public void sendToWelc(){
        if(name.getText().toString().equals("") || password.getText().toString().equals("")){
            pop("please enter full credentials");
        }
        else{
            //check first if the user is even in the preferences list
            if(uman.isUser(name.getText().toString(),password.getText().toString())){
                // if the remember me check box is checked, save the user in sharedpreferences
                if(checkBox.isChecked()){
                    pref.saveRememberedUser(name.getText().toString(),password.getText().toString());
                }
                else{
                    //if it's not checked, make sure that rememberedUser is clear
                    pref.clearRememberedUser();
                } 
                // if the user is in the preferences, then good, we'll send his welcome page

//                pref.saveCurrentUser(name.getText().toString());

                welcome_.intent(this)
                        .name(name.getText().toString())
                        .start();
                uman.closeRealm();
            }
            else{
                // if not, then pop the error in credentials
                pop("Incorrect Credentials");
            }
        }

    }
    public void pop(String s){
        Toast.makeText(this,s, Toast.LENGTH_LONG).show();
    }

}
