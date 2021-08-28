package com.example.bugtracker.memory;

import com.example.bugtracker.Project;
import com.example.bugtracker.dao.ProjectDAO;

import java.util.ArrayList;
import java.util.List;

public class ProjectDAOMemory implements ProjectDAO{
    protected static ArrayList<Project> data = new ArrayList<>();


    @Override
    public void delete(Project p) {
        data.remove(p);
    }

    @Override
    public List<Project> findAll(){
        return data;
    }

    @Override
    public void save(Project p){
        data.add(p);
    }

    @Override
   public Project find(int id){
        for (Project p : data){
            if (Integer.valueOf( p.getProjectId() ).equals(Integer.valueOf( id))){
                return p;
            }
        }
        return null;
    }
}