package com.project.softdev.softdevproject;

import java.io.Serializable;

public abstract class User implements Serializable {

    public User(String username, String password, boolean isSeller){
        this.username=username;
        this.password=password;
        this.isSeller = isSeller;
    }

    public void login(){
        Session.getInstance().userLogin(this);
    }

    public boolean checkUsername(String username){
        return username.equals(this.username);
    }

    public boolean checkPassword(String password){
        return password.equals(this.password);
    }

    public boolean isSeller(){
        return isSeller;
    }

    public String getUsername(){
        return username;
    }

    private String username;
    private String password;
    private boolean isSeller;
}
