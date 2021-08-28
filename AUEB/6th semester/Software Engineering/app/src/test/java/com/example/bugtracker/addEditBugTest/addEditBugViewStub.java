package com.example.bugtracker.addEditBugTest;
import com.example.bugtracker.Bug;
import com.example.bugtracker.view.Bug.AddEditBug.AddEditBugView;

import java.util.Date;

public class addEditBugViewStub implements AddEditBugView{
    private int bugId;
    private String bugName,bugDescription,bugAssigneeUsername,finish,error;
    private Date bugDate;
    private Bug.bugStatus bugStatus;
    private Bug.Priority bugPriority;

    @Override
    public Integer getAttachedBugID(){
        return bugId;
    }

    public String getFinish(){
        return finish;
    }

    @Override
    public String getBugName(){
        return bugName;
    }

    @Override
    public Bug.Priority getBugPriority(){
        return bugPriority;
    }

    @Override
    public Bug.bugStatus getStatus(){
        return bugStatus;
    }

    @Override
    public Date getBugDate(){
        return bugDate;
    }

    @Override
    public String getBugDescription(){
        return bugDescription;
    }

    public String getDevUsername(){
        return bugAssigneeUsername;
    }

    public void setBugId(Integer id){
        bugId = id;
    }

    @Override
    public void setBugName(String value){
        bugName = value;
    }

    public void setBugDescription(String d){
        bugDescription = d;
    }

    public void setBugAssigneeUsername(String u){
        bugAssigneeUsername = u;
    }

    public void setBugDate(Date d){
        bugDate = d;
    }

    public void setBugStatus(Bug.bugStatus stat){
        bugStatus = stat;
    }

    public void setBugPriority(Bug.Priority prio){
        bugPriority = prio;
    }

    @Override
    public void goodFinish(String value){
        finish = value;
    }

    @Override
    public void showError(String msg){
        error = msg;
    }
}
