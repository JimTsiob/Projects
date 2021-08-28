package com.example.bugtracker.view.Project.AddEditProject;

import com.example.bugtracker.Project;
import com.example.bugtracker.dao.ProjectDAO;

import java.text.ParseException;



public class AddEditProjectPresenter {

    private AddEditProjectView view;

    private ProjectDAO projects;

    private Project attachedProject;

    public AddEditProjectPresenter(AddEditProjectView view , ProjectDAO projects){
        this.view = view;
        this.projects = projects;
        Integer attachedProjectID = view.getProjectId();

        if (attachedProjectID != null){
            attachedProject = projects.find(attachedProjectID);
        }
    }

    public void onSaveProject() throws ParseException{
        String projName = view.getProjectName();

        if (attachedProject == null){
            Project tempProj = new Project("web app");
            projects.save(tempProj);
            view.goodFinish("New project with name: " + tempProj.getName() + "has been created");
        }else{
            attachedProject.setName(projName);
            view.goodFinish("Successfully changed name for Project: " + attachedProject.getName());
        }
    }




}
