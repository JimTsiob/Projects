package com.example.bugtracker.view.Bug.AddEditBug;

import com.example.bugtracker.Bug;
import com.example.bugtracker.Developer;
import com.example.bugtracker.dao.BugDAO;
import com.example.bugtracker.dao.DeveloperDAO;

import java.text.ParseException;
import java.util.Date;

public class AddEditBugPresenter {
    //needs a private view to call the view methods
    private AddEditBugView view;
    //needs copy so it can add the new bug
    private BugDAO bugs;
    private DeveloperDAO devs;

    private Bug attachedBug;

    /**
     * Presenter constructor for the addedit bug activity.
     * @param view AddEditView that contains the required methods to handle data.
     * @param bugs BugDAO container
     * @param devs Developer DAO container , both will be used for creating and editing bugs.
     */

    public AddEditBugPresenter(AddEditBugView view, BugDAO bugs , DeveloperDAO devs){
        this.view=view;
        this.bugs=bugs;
        this.devs = devs;
        Integer attachedBugID=view.getAttachedBugID();
        // if attachedbugid = null new bug
        // if not null edit existing bug
        // TODO:make textview for displaying existing bug details
        //find the BUG OBJECT we want to change
        if(attachedBugID!=null) {attachedBug=bugs.find(attachedBugID);}
    }


    /**
     * Method that creates or edits a bug.
     * if the bug doesn't exist , the method creates a new Bug Object and then
     * saves it into the BugDAO object, the user is then shown a success message on screen.
     * If it doesn't exist , the method edits the already existing bug and saves the new
     * data into the BugDAO object.
     * @throws ParseException this exception is handled so that we can use Date objects.
     */

    public void  onSaveBug() throws ParseException {
        //get the view name from the edit text through the activity
        //TODO: get other attributes from edittexts on view
        try{
            String bugName=view.getBugName();
            Bug.Priority prio = view.getBugPriority();
            Bug.bugStatus stat = view.getStatus();
            Date date = view.getBugDate();
            String desc = view.getBugDescription();

            //TODO:get id of developer that changes bug
            if(attachedBug==null){
                Bug tempBug=new Bug(bugName,prio,stat,date,
                        new Developer("Jimbo4","1234567asd89","4or","4th","4th@gmail.com","Developer"),
                        desc
                );
                bugs.save(tempBug);
                view.goodFinish("new bug with name"+ tempBug.getName());
            }
            else
            {
                attachedBug.setName(bugName);
                attachedBug.setSeverity(prio);
                attachedBug.setStatus(stat);
                attachedBug.setIssuanceDate(date);
                attachedBug.setDescription(desc);
                view.goodFinish("Successfully changed bug " +attachedBug.getName());
                //for
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
