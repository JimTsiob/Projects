package com.example.bugtracker.view.Login;

public interface LoginView {
    String getUsername();
    String getPassword();

    void goToStartPage();
    void createToast(String value);

    void signup();
}
