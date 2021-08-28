package com.example.bugtracker.view.Component.ShowComponents;

import android.util.Log;

import com.example.bugtracker.Component;
import com.example.bugtracker.Developer;
import com.example.bugtracker.memory.ComponentDAOMemory;
import com.example.bugtracker.memory.DeveloperDAOMemory;

import java.util.ArrayList;
import java.util.List;

public class ShowComponentsPresenter {
    private ComponentDAOMemory components;
    private ShowComponentsActivity view;
    private Developer AttachedDeveloper;
    private DeveloperDAOMemory devs;

    /**
     * Presenter constructor used for the show components interface.
     * @param view the view with the required methods.
     * @param components ComponentDAO object used for taking the components.
     * @param devs DeveloperDAO used for showing components specifically to users (each CO sees his own Component , PO sees them all).
     */

    public ShowComponentsPresenter(ShowComponentsActivity view, ComponentDAOMemory components, DeveloperDAOMemory devs){
        this.view=view;
        this.components=components;
        this.devs=devs;
        this.AttachedDeveloper=devs.find(view.getAttachedUserName());;


    }

    /**
     * getter method , gets the component's id.
     * @param id the id for search
     * @return the id of the component.
     */

    int onClickItem(int id){
        return components.find(id).getUid();
    }

    /**
     * Method used to filter components between COs and the PO.
     * PO sees them all.
     * CO sees his own component ONLY.
     * @return
     */

    public List<Component> onLoadSource(){

       // List<Component> componentList=components.findAll();
        //for (Component c:componentList){
        //    Log.d("==>>>> component  names",c.getName());}
        //return componentList;

        if (AttachedDeveloper.getRole().equals("Project Owner")) {
            List<Component> componentList = components.findAll();
            for (Component c: componentList) {
                Log.d("*** SCPresenter on load","Project Owner"+ c.getName());
            }
            return componentList;
        }
        else if (AttachedDeveloper.getRole().equals("Component Owner")){
            List<Component> componentList = new ArrayList<>();
            for(Component c:AttachedDeveloper.getAssignedComps()){
                componentList.add(c);
                Log.d("*** SCPresenter on load","Component owner "+AttachedDeveloper.getUsername()+" component "+ c.getName());
            }
            return componentList;
        }
        else {
            List<Component> componentList=new ArrayList<>();

                Log.d("*** SBPresenter on load","Simpledeveloper "+AttachedDeveloper.getUsername()+ "NOthing to display just a simple developer");
                return            componentList;
            }

        }


    /**
     * returns the size of the COmponentDAO object.
     * @return the size of the ComponentDAO object.
     */


    int itemCount(){
        List<Component> componentList=components.findAll();
        return componentList.size();
    }


}
