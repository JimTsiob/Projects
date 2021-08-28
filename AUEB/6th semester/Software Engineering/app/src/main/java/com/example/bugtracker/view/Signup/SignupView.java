package com.example.bugtracker.view.Signup;

public interface SignupView {
    String getFirstName();
    String getLastName();
    String getUsername();
    String getPassword();
    String getEmail();
    String getRole();

    void makeToast(String value);

    void setError(String EditText,String error);

    void goodFinish(String message);
}
