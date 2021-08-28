package com.example.bugtracker.view.HomeActivity;

import android.util.Log;

import com.example.bugtracker.Developer;
import com.example.bugtracker.dao.DeveloperDAO;

public class HomePagePresenter {
    private HomePageView view;
    private DeveloperDAO devs;
    private Developer attachedDeveloper;

    /**
     * Presenter constructor for the home page interface
     * @param view view required for the methods.
     * @param devs DeveloperDAO object to contain the required devs.
     */

    public HomePagePresenter(HomePageView view,DeveloperDAO devs)
    {
        this.view=view;
        this.devs=devs;
        for(Developer d: devs.findAll()){
            Log.d("*** HPPresenter devs", d.getUsername());}
        attachedDeveloper=devs.find(view.getAttachedUsername());
        Log.d("HPPresenter "," got attached developer in Homepagepresenter"+ attachedDeveloper.getUsername());
        view.setUsername(attachedDeveloper.getUsername());
    }

    /**
     * function used to take the user to the Show bugs page.
     */

    public void onClickManageBugs(){
        view.manageBugs();
    }

    /**
     * function used to take the user to the Show Components page.
     */

    public void onClickManageComponents(){view.manageComponents();}

    /**
     * function used to take the user to the Show Projects page.
     */

    public void onClickManageProjects(){view.manageProjects();}

}
