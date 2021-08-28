package com.example.bugtracker.view.Bug.BugDetails;

import android.util.Log;

import com.example.bugtracker.Bug;
import com.example.bugtracker.dao.BugDAO;
import com.example.bugtracker.dao.DeveloperDAO;

public class BugDetailsPresenter  {
    private BugDetailsView view;
    private Bug attachedBug;
    private BugDAO bugs;
    private DeveloperDAO devs;


    /**
     * Presenter constructor used for the BugDetails interface.
     * @param view view used for methods
     * @param Dbugs BugDAO object used to contain the required bugs.
     * @param devs DeveloperDAO object used to contain the required devs.
     */

    public BugDetailsPresenter(BugDetailsView view, BugDAO Dbugs , DeveloperDAO devs)
    {
        this.devs=devs;
        this.view=view;
        this.bugs=Dbugs;
        attachedBug=bugs.find(view.getAttachedBugID());
        view.setBugID(Integer.toString( attachedBug.getId()));
        //returns the bug on which we clicked
        //passes the id of the bug through the intent
        view.set_text_bug_name(attachedBug.getName());
        view.setPriority(attachedBug.getSeverity());
        view.setStatus(attachedBug.getStatus());
        view.setDescription(attachedBug.getDescription());
        view.setIssuanceDate(attachedBug.getIssuanceDate());

    }

    /**
     * Boolean method used to check a user's role.
     * Used to show certain bugs to each developer.
     * And show all bugs to the PO.
     * @return true if role is Project Owner or Component Owner , false otherwise.
     */

    public boolean checkRole()
    {
        boolean temp=false;
        if (devs.find(view.getAttachedUsername())!=null)
        {
            if(devs.find(view.getAttachedUsername()).getRole().equals("Project Owner") ||
                devs.find(view.getAttachedUsername()).getRole().equals("Component Owner"))
            {
                Log.d("*** checkRole", "returns true");
                temp=true;

            }
        }
        else
            {
                Log.d("*** check role", "returns false");
                temp=false;
            }
    return temp;
    }

}
