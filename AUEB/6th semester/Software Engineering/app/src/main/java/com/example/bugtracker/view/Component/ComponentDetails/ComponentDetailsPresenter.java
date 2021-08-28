package com.example.bugtracker.view.Component.ComponentDetails;

import com.example.bugtracker.Component;
import com.example.bugtracker.dao.ComponentDAO;

public class ComponentDetailsPresenter {
    private ComponentDetailsView view;
    private Component attachedComp;
    private ComponentDAO comps;

    /**
     * Presenter object required for the Component Details interface.
     * @param v view required for the methods.
     * @param c ComponentDAO object used to contain the needed components.
     */

    public ComponentDetailsPresenter(ComponentDetailsView v, ComponentDAO c){
        view = v;
        comps = c;
        //find from daos what component we have to display(by its id)
        attachedComp = comps.find(view.getAttachedComponentID());


        //start using view methods to change the names on the layout
        view.setCompName(attachedComp.getName());
    }

    /**
     * Getter method used to get the component's name.
     * @return component's name
     */

    public String getAttachedComponentName(){
        return attachedComp.getName();
    }


}
