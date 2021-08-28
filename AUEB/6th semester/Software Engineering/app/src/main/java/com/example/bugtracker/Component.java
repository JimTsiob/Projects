package com.example.bugtracker;
import java.util.*;

public class Component {
    private String name;
    private int uid;
    public static int counter = 0;

    ArrayList<Bug> compBugs; // ta bugs pou periexei ayto to component.


    public Component(String n){
        name = n;
        compBugs = new ArrayList<>();
        uid=++counter;
    }

    /**
     * Getter method
     * @return the component's bugs list.
     */

    public ArrayList<Bug> getCompBugs(){
        return compBugs;
    }

    /**
     * Getter method
     * @return the component's name
     */

    public String getName(){
        return name;
    }

    /**
     * Setter method
     * @param n new name
     */

    public void setName(String n){
        name = n;
    }

    /**
     * Getter method
     * @return the comp's id
     */

    public int getUid(){return uid;}

    /**
     * method to add bugs to the component.
     * @param b the added bug.
     */

    public void  addBug(Bug b){
        compBugs.add(b);
    }

}
