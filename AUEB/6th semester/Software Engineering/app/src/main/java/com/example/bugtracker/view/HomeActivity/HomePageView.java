package com.example.bugtracker.view.HomeActivity;

public interface HomePageView {

    public void manageProjects();
    public void manageComponents();
    public void manageBugs();
    public String getAttachedUsername();

    //TODO later with login
    public void mangeProfile();


    void setUsername(String value);
}
