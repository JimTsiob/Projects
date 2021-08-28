package com.example.bugtracker.dao;

import com.example.bugtracker.Bug;
import com.example.bugtracker.Component;
import com.example.bugtracker.Developer;
import com.example.bugtracker.Project;
import com.example.bugtracker.memory.BugDAOMemory;
import com.example.bugtracker.memory.ComponentDAOMemory;
import com.example.bugtracker.memory.DeveloperDAOMemory;
import com.example.bugtracker.memory.ProjectDAOMemory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Initializer {
    private static BugDAO bugDAO;
    private static ComponentDAO componentDAO;
    private static DeveloperDAO developerDAO;
    private static ProjectDAO projectDAO;

    public Initializer() throws ParseException {
      bugDAO=new BugDAOMemory();
       componentDAO=new ComponentDAOMemory();
        developerDAO=new DeveloperDAOMemory();
         projectDAO=new ProjectDAOMemory();

    }

    /**
     *
     * deletes all daomemory data
     */

    protected void erase(){
        ArrayList<Bug> bugs=new ArrayList<>(bugDAO.findAll());
        for (Bug b:bugs) {bugDAO.delete(b);}

        ArrayList<Component> comps=new ArrayList<>(componentDAO.findAll());
        for(Component c:comps){componentDAO.delete(c);}

        ArrayList<Developer> devs=new ArrayList<>(developerDAO.findAll());
        for(Developer d:devs){developerDAO.delete(d);}

        ArrayList<Project> projects=new ArrayList<>(projectDAO.findAll());
        for(Project p:projects){projectDAO.delete(p);}
    }

    public void preparedata() throws ParseException {
        erase();

        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);

        String date2 = "01/09/2020";
        Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse(date2);


        Developer co2 = new Developer("1ComponentOwner2","123456789","Jim","Beam","hihi@gmail.com","Component Owner");
        Developer co1 = new Developer("2ComponentOwner1","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Developer po = new Developer("3ProjectOwner1","123456789","Project", "Owner", "projowner@gmail.com", "Project Owner");
        Developer dev4 = new Developer("4dev","123456789","sJim","Besam","hihi@gmail.com","Developer");
        Developer dev5 = new Developer("5dev","123456789","dfJim","Bdfeam","hihi@gmail.com","Developer");
        Bug b1=  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, co1, "Pesky little bug!!");
        Bug b2=new Bug("backend problem",Bug.Priority.Low,Bug.bugStatus.Done,d2,co2,"Serious bug in interface");

        Bug b3=new Bug("backend problem 3",Bug.Priority.Low,Bug.bugStatus.InProgress,d2,dev5,"Serious bug in interface");
        Bug b4=new Bug("backend problem 4",Bug.Priority.Low,Bug.bugStatus.InProgress,d2,dev4,"Serious bug in interface");
        Bug b5=new Bug("backend problem 5",Bug.Priority.Low,Bug.bugStatus.ToDo,d2,dev4,"Serious bug in interface");

        Project p = new Project("web app");
        Component c1=new Component("Component1");
        Component c2=new Component("Component2");
        po.addComponentToProject(c1,p);
        po.addComponentToProject(c2,p);

        po.assignComponentToDev(c1,co1);
        po.assignComponentToDev(c2,co2);

        co1.assignBug(b4,dev4);
        c1.addBug(b4);

        co2.assignBug(b3,dev5);
        c2.addBug(b3);



       // BugDAOMemory bugdao=new BugDAOMemory();
        bugDAO.save(b1);
        bugDAO.save(b2);
        bugDAO.save(b3);
        bugDAO.save(b4);
        bugDAO.save(b5);

        //DeveloperDAOMemory developerDAO=new DeveloperDAOMemory();
        developerDAO.save(co2);
        developerDAO.save(co1);
        developerDAO.save(po);
        developerDAO.save(dev4);
        developerDAO.save(dev5);

        //ProjectDAOMemory projectDAO = new ProjectDAOMemory();
        projectDAO.save(p);

        //ComponentDAOMemory componentDAO=new ComponentDAOMemory();
        componentDAO.save(c1);
        componentDAO.save(c2);

    }

    public DeveloperDAO getDevDAO(){
        return developerDAO;
    }

    public BugDAO getBugDAO(){
        return bugDAO;
    }

    public ComponentDAO getComponentDAO(){
        return componentDAO;
    }


}
