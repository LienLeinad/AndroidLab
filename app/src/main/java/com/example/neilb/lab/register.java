package com.example.neilb.lab;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_register)
public class register extends AppCompatActivity {
    @ViewById(R.id.textView)
    TextView registerText;

    @ViewById(R.id.userEdit)
    EditText userEdit;

    @ViewById(R.id.passwordEdit)
    EditText passwordEdit;

    @ViewById(R.id.eMailEdit)
    EditText eMailEdit;

    @ViewById(R.id.editButton)
    Button registerButton;

    @ViewById(R.id.cancelButton)
    Button cancelButton;

    @Bean
    UserManager uman;

    @AfterViews
    protected void init() {

    }

    @Click(R.id.editButton)
    public void registerUser(){
        String name = userEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String email = eMailEdit.getText().toString();
        if(!name.equals("") && !password.equals("") && !email.equals("")){
            UserObject u = uman.createUser(name, password, email);

            if(uman.isInList(u.getUsername())){

                pop("Username already being used, please use a different username");
            }
            else{
                uman.addUser(u);
                uman.closeRealm();
                RegHome_.intent(this).start();
                finish();
            }
        }
        else{
            pop("All fields are required!");
        }
    }

    // Closes Registration Page and sends you back to the shadow realm
    @Click(R.id.cancelButton)
    public void cancel(){
        this.finish();
        RegHome_.intent(this).start();
    }
    public void pop(String s){
        Toast.makeText(this,s, Toast.LENGTH_LONG).show();
    }

}
