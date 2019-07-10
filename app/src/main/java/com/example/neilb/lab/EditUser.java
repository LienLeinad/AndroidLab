package com.example.neilb.lab;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.edit_user)
public class EditUser extends AppCompatActivity {
    @ViewById(R.id.User)
    TextView user;
    @ViewById(R.id.passwordEdit)
    EditText passwordEdit;
    @ViewById(R.id.eMailEdit)
    EditText emailEdit;
    @Extra
    String username;

    @Bean
    UserManager uman;
    private UserObject EditedUser;
    private String UUID;
    @AfterViews
    public void init(){
        EditedUser = uman.getUser(username);
        UUID = EditedUser.getUuid();
        user.setText(username);

        // Set text Edits to current values
        passwordEdit.setText(EditedUser.getPassword());
        emailEdit.setText(EditedUser.getEmail());
        

    }

    @Click(R.id.cancelButton)
    public void cancelEdit(){
        RegHome_.intent(this).start();
        uman.closeRealm();
        finish();
    }
    @Click(R.id.editButton)
    public void saveEdit(){
        UserObject u = new UserObject();
        u.setUsername(username);
        u.setPassword(passwordEdit.getText().toString());
        u.setEmail(emailEdit.getText().toString());
        u.setUuid(UUID);

        uman.addUser(u);
        uman.closeRealm();
        finish();
        RegHome_.intent(this).start();


    }




}
