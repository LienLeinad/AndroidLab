package com.example.neilb.lab;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserObject extends RealmObject {
    @PrimaryKey
    private String uuid;

    private String username;
    private String password;
    private String email;

    @Override
    public String toString() {
        return "UserObject{" +
                "uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
