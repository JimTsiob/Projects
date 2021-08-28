package com.example.bugtracker;
import java.util.ArrayList;

public class Project {
    private int project_id;
    public static int counter = 0; // gia distinct id.
    private String name;

    ArrayList<Developer> users; // to project exei 1 h perissoterous xrhstes pou douleyoun panw se ayto.

    ArrayList<Component> projectComps; // ta components pou exei to project (1 h perissotera).

    ArrayList<Bug> tickets; // ta bugs pou exei to project.

    public Project(String n){
        project_id = ++counter;
        name = n;
        users = new ArrayList<>();
        projectComps = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    /**
     * getter method
     * @return project's name
     */

    public String getName(){
        return name;
    }

    /**
     * getter method
     * @return project's id.
     */

    public int getProjectId(){
        return project_id;
    }

    /**
     * setter method
     * @param n new project name
     */

    public void setName(String n){
        name = n;
    }

    /**
     * getter method
     * @return project's bug list
     */

    public ArrayList<Bug> getTickets(){
        return tickets;
    }

    /**
     * getter method
     * @return project's components list
     */

    public ArrayList<Component> getComps(){
        return projectComps;
    }

    /**
     * getter method
     * @return the project's developers
     */

    public ArrayList<Developer> getDevs(){
        return users;
    }

    /**
     * method that adds new components to the project.
     * @param component the component to be added.
     */

    public void addComponent(Component component){this.projectComps.add(component);}
}
