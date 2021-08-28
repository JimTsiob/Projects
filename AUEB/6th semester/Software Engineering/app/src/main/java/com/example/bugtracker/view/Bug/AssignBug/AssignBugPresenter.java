package com.example.bugtracker.view.Bug.AssignBug;

import com.example.bugtracker.Bug;
import com.example.bugtracker.Developer;
import com.example.bugtracker.dao.BugDAO;
import com.example.bugtracker.dao.DeveloperDAO;
import com.example.bugtracker.memory.BugDAOMemory;
import com.example.bugtracker.memory.DeveloperDAOMemory;

public class AssignBugPresenter {
    private BugDAO bugs;
    private DeveloperDAO devs;
    private AssignBugActivity view;
    private Bug AttachedBug;
    private Developer AttachedDeveloper;

    /**
     * Presenter constructor used for the Assign Bug interface.
     * @param assignBugActivity activity that's used for the methods.
     * @param bugDAOMemory BugDAO object to contain all bugs.
     * @param developerDAOMemory DeveloperDAO Object to contain the needed developers.
     */

    public AssignBugPresenter(AssignBugActivity assignBugActivity, BugDAOMemory bugDAOMemory, DeveloperDAOMemory developerDAOMemory) {
    this.bugs=bugDAOMemory;
    this.devs=developerDAOMemory;
    this.view=assignBugActivity;

    if(Integer.valueOf(view.getAttachedBugID())!=null){AttachedBug=bugs.find(view.getAttachedBugID());}
    if(view.getAttachedUsername()!=null) {
        AttachedDeveloper = devs.find(view.getAttachedUsername());
    }
    }

    /**
     * Implementation of onClickAssign method , once the assign button is pressed
     * the bug will be assigned to the corresponding developer. If the user-to-be-assigned the bug is not a developer
     * the PO or CO will be shown a failure message.
     */

    public void onClickAssign() {
        if(devs.find(view.getAsignee())!=null) {
            AttachedDeveloper.assignBug(AttachedBug, devs.find(view.getAsignee()));
            view.goodFinish();
        }
        else {view.badFinish();}
    }
}
