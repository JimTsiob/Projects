package com.example.bugtracker.view.Signup;
import com.example.bugtracker.dao.DeveloperDAO;
import com.example.bugtracker.memory.DeveloperDAOMemory;
import com.example.bugtracker.Developer;
import java.util.List;

import java.util.regex.Pattern;

public class SignupPresenter {
    private SignupView view;
    private DeveloperDAO devs;

    public SignupPresenter(SignupView view, DeveloperDAO devs){
        this.view = view;
        this.devs = devs;
    }


    /**
     * This function makes sure that all validations
     * are correct and then proceeds to save the new developer
     * object created from the signup form.
     * It also shows a message to the user if the signup
     * was successful or not.
     */
    public void add(){
        if (validateFirstName() && validateLastName() && validateUsername() && validatePassword() && validateEmail() && validateRole()){
            Developer newDev = new Developer(view.getUsername(),view.getPassword(),view.getFirstName(),view.getLastName(),view.getEmail(),view.getRole());
            devs.save(newDev);
            view.goodFinish("Signup successful.");
        }else{
            view.makeToast("Please recheck the fields");
        }
    }

    /**
     * Checks if first name is empty.
     *
     */
    private boolean validateFirstName(){
        String fn = view.getFirstName().trim();
        if (fn.isEmpty()){
            view.setError("firstname","First name cannot be empty.");
            return false;
        }else{
            view.setError("firstname",null);
            return true;
        }
    }

    /**
     * Checks if last name is empty.
     *
     */
    private boolean validateLastName(){
        String ln = view.getLastName().trim();
        if (ln.isEmpty()){
            view.setError("lastname","Last name cannot be empty.");
            return false;
        }else{
            view.setError("lastname",null);
            return true;
        }
    }

    /**
     * Checks if email is empty
     * or if it follows this pattern : String@String.String (2-6 chars for last string)
     */
    private boolean validateEmail(){
        String email = view.getEmail().trim();
        Pattern email_pattern =  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if (email.isEmpty()){
            view.setError("email","Email must not be empty.");
            return false;
        }else if (!email_pattern.matcher(email).matches()){
            view.setError("email","Invalid email");
            return false;
        }else{
            view.setError("email",null);
            return true;
        }
    }


    /**
     * Checks if username is empty or not.
     *
     */
    private boolean validateUsername(){
        String username = view.getUsername().trim();
        if (username.isEmpty()){
            view.setError("username","Username cannot be empty.");
            return false;
        }else{
            view.setError("username",null);
            return true;
        }
    }

    /**
     * Checks if password has one capital letter
     * one small letter , one number and is at least 8 characters long.
     */
    private boolean validatePassword(){
        String password = view.getPassword().trim();
        Pattern PASSWORD_PATTERN = Pattern.compile("[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");
        if (password.isEmpty()){
            view.setError("password","Password cannot be empty.");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(password).matches()){
            view.setError("password","Invalid password");
            return false;
        }else{
            view.setError("password",null);
            return true;
        }
    }


    /**
     * Checks if role is empty,
     * or if it matches one of the 3 roles.
     * Component Owner , Project Owner or Developer.
     */
    private boolean validateRole(){
        String role = view.getRole().trim();
        String PO = "Project Owner";
        String CO = "Component Owner";
        String dev = "Developer";
        if (role.isEmpty()){
            view.setError("role","Role cannot be empty.");
            return false;
        }else if (role.equalsIgnoreCase(PO) || role.equalsIgnoreCase(CO) || role.equalsIgnoreCase(dev)){
            view.setError("role",null);
            return true;
        }
        view.setError("role","Invalid role");
        return false;
    }
}
