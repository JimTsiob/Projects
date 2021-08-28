package com.example.bugtracker.signupTest;
import android.os.Bundle;

import com.example.bugtracker.view.Signup.SignupView;

public class SignupViewStub implements SignupView{
    private String firstname,lastname,username,password,email,role,error,errorobject,finish;


    public void setFirstname(String fn){
        firstname = fn;
    }

    public void setLastname(String ln){
        lastname = ln;
    }

    public void setUsername(String u){
        username = u;
    }

    public void setPassword(String p){
        password = p;
    }

    public void setEmail(String e){
        email = e;
    }

    public void setRole(String r){
        role = r;
    }

    @Override
    public void setError(String et, String error){
        this.errorobject = et;
        this.error = error;
    }

    @Override
    public void goodFinish(String msg){
        finish = msg;
    }

    @Override
    public String getFirstName(){
        return firstname;
    }

    @Override
    public String getLastName(){
        return lastname;
    }

    @Override
    public String getEmail(){
        return email;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getRole(){
        return role;
    }

    public String getFinish(){
        return finish;
    }

    public String getError(){
        return error;
    }

    @Override
    public void makeToast(String value){

    }

}
