package com.example.bugtracker.view.Login;

import android.util.Log;

import com.example.bugtracker.Developer;
import com.example.bugtracker.dao.DeveloperDAO;
import com.example.bugtracker.memory.DeveloperDAOMemory;

import java.util.ArrayList;
import java.util.List;

public class LoginPresenter {
    private LoginView view;
    private DeveloperDAO devs;


    /**
     * Presenter constructor for the login screen.
     * @param view Login view in order to get data.
     * @param devs DeveloperDAO object that holds Developer data
     */

    public LoginPresenter(LoginView view, DeveloperDAO devs){
        this.view = view;
        this.devs = devs;
        /*for (Developer d:devs.findAll()){
            Log.d("*** devs", d.getUsername());
            Log.d("*** passwords", d.getPassword());
        }*/
    }

    /**
     * The main method , login , takes the user's username and password.
     * If the user with the given credentials exists , the user
     * receives an on screen message of the login success
     * and is sent to the main page. Otherwise he's shown
     * a message of failure.
     * @param username the user's username
     * @param password the user's password
     */

    public void login(String username, String password){
        //Log.d("*** presenter.login","yes");
        List<Developer> existingDevs = devs.findAll();
        for (Developer d : existingDevs ){
            String user = d.getUsername();
            String pass = d.getPassword();
            //Log.d("***found user-pass", "yes");
            if (username.equals(user) && password.equals(pass)){

                view.createToast("Logged in!");
                view.goToStartPage();
                return;
            }
        }
        view.createToast("Wrong username or password");
    }

    /**
     * Method that gets us to the signup page.
     */

    public void signup(){
        view.signup();
    }

    /**
     * method that is executed when the user presses the
     * "FORGOT PASSWORD" button. If the user with the said
     * username exists , he's shown the password.
     * Otherwise , the screen shows "Username doesn't exist" message.
     * @param usernameFromForm the user's input (username)
     * @return
     */

    public String forgot(String usernameFromForm){
        List<Developer> existingDevs = devs.findAll();
        for (Developer d : existingDevs) {
            String usernameFromdao = d.getUsername();
            //Log.d("\n***in forgot dev",usernameFromdao);
            if (usernameFromForm.equals(usernameFromdao)){
                //Log.d("***46", "there is user with username "+usernameFromdao);
                view.createToast(d.getPassword());
                return d.getPassword();
            }
        }
        view.createToast("Username doesn't exist!");
        return "Username doesn't exist";
    }

}
