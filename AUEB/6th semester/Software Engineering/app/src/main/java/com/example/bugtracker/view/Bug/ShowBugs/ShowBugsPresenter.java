package com.example.bugtracker.view.Bug.ShowBugs;

import android.util.Log;

import com.example.bugtracker.Bug;
import com.example.bugtracker.Component;
import com.example.bugtracker.Developer;
import com.example.bugtracker.dao.BugDAO;
import com.example.bugtracker.dao.DeveloperDAO;
import com.example.bugtracker.memory.BugDAOMemory;
import com.example.bugtracker.memory.DeveloperDAOMemory;

import java.util.ArrayList;
import java.util.List;

public class ShowBugsPresenter {
    private BugDAO bugs;
    private ShowBugsActivity view;
    private Developer AttachedDeveloper;
    private DeveloperDAO devs;

    /**
     * Presenter constructor used for the show bugs interface.
     * @param view the required ShowBugsActivity view for the methods.
     * @param bugs BugDAO container for required bugs.
     * @param devs DeveloperDAO container for required dev manipulation.
     */

    public ShowBugsPresenter(ShowBugsActivity view, BugDAOMemory bugs, DeveloperDAOMemory devs) {
        this.view = view;
        this.bugs = bugs;
        this.devs = devs;

        if(view.getAttachedUsername()!=null){
        AttachedDeveloper = devs.find(view.getAttachedUsername());
       // Log.d("*** SBPresenter", "found this developer " + devs.find(view.getAttachedUsername()).getUsername());
    }}

    /**
     * Method used to find bugs
     * @param id the bug id
     * @return the id of the required bug.
     */

    int onClickItem(int id) {
        return bugs.find(id).getId();
    }

    /**
     * Method used to load bugs on the list , depending on the role of the user.
     * PO can see them all.
     * CO can see his component's bugs.
     * Developer can see his assigned bugs only.
     * @return
     */

    public List<Bug> onLoadSource() {
        List<Bug> toDoBugList=new ArrayList<>();
        for (Bug b:bugs.findAll()) { if (b.getStatus().equals(Bug.bugStatus.ToDo))
            toDoBugList.add(b);

        }
        if (AttachedDeveloper.getRole().equals("Project Owner")) {
            List<Bug> bugList = bugs.findAll();
            for (Bug b : bugList) {
                Log.d("*** SBPresenter on load", b.getName());
            }
            return bugList;
        }
        else if (AttachedDeveloper.getRole().equals("Component Owner")){
            List<Bug> bugList = new ArrayList<>();
            for (Component c:AttachedDeveloper.getAssignedComps()){
                for(Bug b:c.getCompBugs()){
                    bugList.add(b);
                }
            }
            for (Bug b : bugList) {
                Log.d("*** SBPresenter on load","Component owner "+AttachedDeveloper.getUsername()+ b.getName());
            }
            for(Bug b :toDoBugList){
                bugList.add(b);
            }
            return bugList;
        }
        else {
            List<Bug> bugList=new ArrayList<>();
            for (Bug b:AttachedDeveloper.getAssignedBugs()){
                bugList.add(b);
            }
            for (Bug b : bugList) {
                Log.d("*** SBPresenter on load","developer "+AttachedDeveloper.getUsername()+ b.getName());
            }
        return bugList;
        }
    }


    /**
     * Method used to return the size of the BugDAO object.
     * @return the size of the BugDAO object.
     */


    int itemCount(){
        List<Bug> bugList=bugs.findAll();
        return bugList.size();
    }




}
