package com.example.bugtracker.view.Login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bugtracker.R;
import com.example.bugtracker.dao.Initializer;
import com.example.bugtracker.memory.DeveloperDAOMemory;
import com.example.bugtracker.view.HomeActivity.HomePageActivity;
import com.example.bugtracker.view.Signup.SignupActivity;

import java.text.ParseException;

public class LoginActivity extends AppCompatActivity implements LoginView{

     LoginPresenter presenter;
    private static  boolean daoDataIsInitialised=false;

    /**
     * Creates the required activity for the login screen.
     * @param savedInstanceState
     */

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(!daoDataIsInitialised) {
            try {
                Initializer init = new Initializer();
                init.preparedata();
                daoDataIsInitialised=true;
                Log.d("*** data initialised","true!!!!!");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Button login = findViewById(R.id.btn_login);
        Button forgotPass = findViewById(R.id.btn_forgot);
        Button signup = findViewById(R.id.btn_signup2);

        presenter = new LoginPresenter(this,new DeveloperDAOMemory());
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                presenter.login(getUsername(),getPassword());
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                presenter.signup();
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new AlertDialog.Builder(LoginActivity.this)
                        .setCancelable(true)
                        .setTitle("Password for user " + getUsername() + " is: ")
                        .setMessage(presenter.forgot(getUsername()))
                        .setPositiveButton("OK", null).create().show();
            }
        });

    }

    /**
     * Method to take the user to the signup activity
     * if he presses the signup button.
     */

    public void signup(){
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
    }

    /**
     * Once the login procedure ends , the user is sent
     * to the main app page.
     */
    public void goToStartPage(){

        Intent intent = new Intent(this,HomePageActivity.class);
        intent.putExtra("EXTRA_USERNAME",getUsername());
        Log.d("*** LA","successfully init intent put extra");
        startActivity(intent);
    }

    /**
     * function to show messages
     * @param value the message
     */

    public void createToast(String value){
        Toast.makeText(this,value,Toast.LENGTH_SHORT).show();
    }

    /**
     * getter method used for the presenter.
     * @return
     */

    public String getUsername(){
        return ((EditText) findViewById(R.id.username_login)).getText().toString();
    }

    /**
     * getter method used for the presenter.
     * @return
     */

    public String getPassword(){
        return ((EditText) findViewById(R.id.password_login)).getText().toString();
    }

}
