package com.example.bugtracker.view.Project.ProjectDetails;

import com.example.bugtracker.Project;
import com.example.bugtracker.dao.ProjectDAO;

public class ProjectDetailsPresenter {
    private ProjectDetailsView view;
    private Project attachedProject;
    private ProjectDAO projects;

    public ProjectDetailsPresenter(ProjectDetailsView view , ProjectDAO projects){
        this.view = view;
        this.projects = projects;
        attachedProject = projects.find(view.getAttachedProjectId());
        view.setTextProjectName(attachedProject.getName());
    }
}
