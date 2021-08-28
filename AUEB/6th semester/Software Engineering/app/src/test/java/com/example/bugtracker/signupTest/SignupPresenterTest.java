package com.example.bugtracker.signupTest;

import com.example.bugtracker.Developer;
import com.example.bugtracker.dao.DeveloperDAO;
import com.example.bugtracker.dao.Initializer;
import com.example.bugtracker.memory.DeveloperDAOMemory;
import com.example.bugtracker.view.Signup.SignupPresenter;
import com.example.bugtracker.view.Signup.SignupView;


import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class SignupPresenterTest {

    private SignupPresenter presenter;
    private SignupViewStub view;



    /**
     * full signup test , checks all parameters.
     */
    @Test
    public void testSignup(){
        try{
            Initializer init = new Initializer();
            init.preparedata();
            DeveloperDAO devList = init.getDevDAO();
            view = new SignupViewStub();
            presenter = new SignupPresenter(view,devList);

            view.setFirstname("");
            presenter.add();
            assertEquals("First name cannot be empty.",view.getError());

            view.setFirstname("Jim");
            view.setLastname("");
            presenter.add();
            assertEquals("Last name cannot be empty.",view.getError());

            view.setLastname("Tsiob");
            view.setUsername("");
            presenter.add();
            assertEquals("Username cannot be empty.",view.getError());

            view.setUsername("jimbo");
            view.setPassword("");
            presenter.add();
            assertEquals("Password cannot be empty.",view.getError());

            view.setPassword("bl");
            presenter.add();
            assertEquals("Invalid password",view.getError());


            view.setPassword("Batmanakos1!");
            view.setEmail("");
            presenter.add();
            assertEquals("Email must not be empty.",view.getError());

            view.setEmail("haha");
            presenter.add();
            assertEquals("Invalid email",view.getError());

            view.setEmail("haha@gmail.com");
            view.setRole("");
            presenter.add();
            assertEquals("Role cannot be empty.",view.getError());

            view.setRole("Macaroni");
            presenter.add();
            assertEquals("Invalid role",view.getError());

            view.setRole("Component Owner");
            presenter.add();
            assertEquals("Signup successful.",view.getFinish());

        }catch(ParseException e){
            e.printStackTrace();
        }


    }

}
