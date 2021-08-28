package com.example.bugtracker.view.Bug.AddEditBug;

import com.example.bugtracker.Bug;

import java.util.Date;

public interface AddEditBugView {

    Integer getAttachedBugID();
    String getBugName();
    Bug.Priority getBugPriority();
    Bug.bugStatus getStatus();
    Date getBugDate() throws Exception;
    String getBugDescription();
    //String getDevUsername();
    void setBugName(String name);
    void goodFinish(String msg);
    void showError(String msg);

}
