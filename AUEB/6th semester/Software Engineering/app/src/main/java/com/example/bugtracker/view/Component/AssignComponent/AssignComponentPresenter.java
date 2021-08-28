package com.example.bugtracker.view.Component.AssignComponent;

import com.example.bugtracker.Component;
import com.example.bugtracker.Developer;
import com.example.bugtracker.dao.ComponentDAO;
import com.example.bugtracker.dao.DeveloperDAO;
import com.example.bugtracker.memory.ComponentDAOMemory;
import com.example.bugtracker.memory.DeveloperDAOMemory;

public class AssignComponentPresenter {
    private AssignComponentActivity view;

    private ComponentDAO comps;
    private DeveloperDAO devs;
    private Developer attachedDev;
    private Component attachedComp;

    public AssignComponentPresenter(AssignComponentActivity view, ComponentDAOMemory comps, DeveloperDAOMemory devs){
        this.view = view;
        this.comps = comps;
        this.devs = devs;

        if (Integer.valueOf(view.getAttachedCompID()) != null){
            attachedComp = comps.find(view.getAttachedCompID());
        }
        if (view.getAttachedUsername() != null){
            attachedDev = devs.find(view.getAttachedUsername());
        }
    }

    public void onClickAssignCom(){
        if (devs.find(view.getCOAssignee()) != null){
            /*if (attachedDev.getRole().equalsIgnoreCase("Developer")){
                view.wrongRoleFinish();
            }*/
            attachedDev.assignComponentToDev(attachedComp,devs.find(view.getCOAssignee()));
        }else{
            view.badFinish();
        }
    }


}
