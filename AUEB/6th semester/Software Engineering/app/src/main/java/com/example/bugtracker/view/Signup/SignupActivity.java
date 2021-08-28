package com.example.bugtracker.view.Signup;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.bugtracker.R;
import com.example.bugtracker.dao.Initializer;
import com.example.bugtracker.memory.DeveloperDAOMemory;

public class SignupActivity extends AppCompatActivity implements SignupView {
    Button signupButton;
    SignupPresenter presenter;

    /**
     * Creates the required interface for signup screen.
     * @param savedInstanceState
     */

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        presenter = new SignupPresenter(this,new DeveloperDAOMemory());
        signupButton = findViewById(R.id.btn_signup);
        signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                presenter.add();
            }
        });
    }

    /**
     * Method that prints a message on screen.
     * @param msg the message
     */

    public void makeToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * Method that is used to handle all the different errors
     * in their respective fields and to print the correct
     * message to the user (for validation purposes).
     * @param et the Edit text id (to take input from each different EditText object in the XML file)
     * @param error the error message to be shown to the user
     */

    public void setError(String et,String error){
        switch (et){
            case "firstname":
                ((EditText) findViewById(R.id.firstname)).setError(error);
                break;
            case "lastname":
                ((EditText) findViewById(R.id.lastname)).setError(error);
                break;
            case "email":
                ((EditText) findViewById(R.id.email)).setError(error);
                break;
            case "username":
                ((EditText) findViewById(R.id.username)).setError(error);
                break;
            case "password":
                ((EditText) findViewById(R.id.password)).setError(error);
                break;
            case "role":
                ((EditText) findViewById(R.id.role)).setError(error);
                break;
        }
    }

    /**
     * Message to be shown on screen in case of success.
     * @param message the messsage
     */

    public void goodFinish(String message){
        Intent intent_signup = new Intent();
        intent_signup.putExtra("msg_to_toast",message);
        setResult(RESULT_OK,intent_signup);
        finish();
    }

    /**
     * Getter method for presenter.
     * implemented from the SignupView file.
     * @return
     */

    public String getUsername(){
        return ((EditText) findViewById(R.id.username)).getText().toString();
    }

    /**
     * Getter method for presenter.
     * implemented from the SignupView file.
     * @return
     */

    public String getPassword(){
        return ((EditText) findViewById(R.id.password)).getText().toString();
    }

    /**
     * Getter method for presenter.
     * implemented from the SignupView file.
     * @return
     */

    public String getFirstName(){
        return ((EditText) findViewById(R.id.firstname)).getText().toString();
    }

    /**
     * Getter method for presenter.
     * implemented from the SignupView file.
     * @return
     */

    public String getLastName(){
        return ((EditText) findViewById(R.id.lastname)).getText().toString();
    }

    /**
     * Getter method for presenter.
     * implemented from the SignupView file.
     * @return
     */

    public String getEmail(){
        return ((EditText) findViewById(R.id.email)).getText().toString();
    }

    /**
     * Getter method for presenter.
     * implemented from the SignupView file.
     * @return
     */

    public String getRole(){
        return ((EditText) findViewById(R.id.role)).getText().toString();
    }
}
