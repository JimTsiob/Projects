package com.example.bugtracker.loginTest;

import com.example.bugtracker.Developer;
import com.example.bugtracker.dao.DeveloperDAO;
import com.example.bugtracker.dao.Initializer;
import com.example.bugtracker.memory.DeveloperDAOMemory;
import com.example.bugtracker.view.Login.LoginPresenter;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

public class LoginPresenterTest {
    private LoginViewStub view;
    private LoginPresenter presenter;

    @Test
    public void loginForgotAndSignupTest(){
        try{
            // user exists.

            Initializer init = new Initializer();
            init.preparedata();
            view = new LoginViewStub();
            DeveloperDAO devs = init.getDevDAO();
            presenter = new LoginPresenter(view,devs);
            view.setUsername("4dev");
            view.setPassword("123456789");
            presenter.login(view.getUsername(),view.getPassword());
            assertEquals("Logged in!",view.getToast());

            // user does not exist.

            view.setUsername("bla");
            view.setPassword("2123125195");
            presenter.login(view.getUsername(),view.getPassword());
            assertEquals("Wrong username or password",view.getToast());

            // forgot usage : user does not exist

            assertEquals("Username doesn't exist",presenter.forgot(view.getUsername()));

            // forgot : user exists

            view.setUsername("4dev");
            assertEquals("123456789",presenter.forgot(view.getUsername()));

            // signup test

            view.signup();
            assertEquals(1,view.getTimesClickedForSignup());

        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}
