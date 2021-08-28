package com.example.bugtracker.loginTest;

import com.example.bugtracker.view.Login.LoginView;

public class LoginViewStub implements LoginView {
    String username,password,toast;
    int clicks;

    @Override
    public void goToStartPage(){

    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public void createToast(String value){
        toast = value;
    }


    public void setUsername(String v){
        username = v;
    }

    public void setPassword(String v){
        password = v;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public void signup(){
        clicks++;
    }

    public int getTimesClickedForSignup(){
        return clicks;
    }


    public String getToast(){
        return toast;
    }
}
