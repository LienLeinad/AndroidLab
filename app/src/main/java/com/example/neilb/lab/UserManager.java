package com.example.neilb.lab;

import org.androidannotations.annotations.EBean;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

@EBean
public class UserManager {
    private Realm realm;
//    private RealmResults<UserObject> userList;
    public UserManager(){
        // Opens the Realm Instance
        realm = Realm.getDefaultInstance();


    }

    public UserObject createUser(String name, String password, String email){
        UserObject u = new UserObject();
        u.setUuid(UUID.randomUUID().toString());
        u.setUsername(name);
        u.setPassword(password);
        u.setEmail(email);
        return u;
    }


    public Boolean isUser(String name, String password){
        RealmResults<UserObject> userList = getUserList();
        if(realm.where(UserObject.class).count() == 0){
            return false;
        }

        // check if the username is in the userList
        if(!isInList(name)){
            System.out.println("Username not found");
            return false;
        }
        //if username is in the list, check if password is correct

        return comparePass(name, password);
    }

    private Boolean comparePass(String name, String password){
        RealmResults<UserObject> userList = getUserList();
        for(UserObject u: userList){
            if(u.getUsername().equals(name)&&u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    // adds user into userList in realm
    public void addUser(UserObject u){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(u);
        realm.commitTransaction();
    }

    // returns list of users
    public RealmResults<UserObject> getUserList(){
        RealmResults<UserObject> userList = realm.where(UserObject.class).findAll();
        return userList;
    }

    // checks if inputted username is already in the userList
    public Boolean isInList(String name){
        RealmResults<UserObject> userList = getUserList();
        System.out.println("isInList method");
        for(UserObject u: userList){
            if(u.getUsername().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void deletDis(UserObject u){
        realm.beginTransaction();
        try {
            u.deleteFromRealm();
        } catch (Exception e) {
            e.printStackTrace();
        }
        realm.commitTransaction();
    }
    public void closeRealm(){
        realm.close();
    }

    public UserObject getUser(String name){
        RealmResults<UserObject> userList = getUserList();
        UserObject result = null;
        for(UserObject u: userList){
            if(u.getUsername().equals(name)){
                result = u;
                return result;
            }
        }
        return result;
    }

}
