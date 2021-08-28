package com.example.bugtracker.memory;

import com.example.bugtracker.Developer;
import com.example.bugtracker.dao.DeveloperDAO;

import java.util.ArrayList;


public class DeveloperDAOMemory implements DeveloperDAO{
    protected static ArrayList<Developer> data = new ArrayList<>();

    @Override
    public void delete(Developer dev){
        for (Developer d : data){
            if (Integer.valueOf( dev.getId()).equals( Integer.valueOf(d.getId()))){
                data.remove(dev);
            }
        }
    }

    @Override
   public ArrayList<Developer> findAll(){
        return data;
    }

    @Override
   public  void save(Developer dev){
        data.add(dev);
    }

    public Developer find(String username){
        for (Developer d : data){
            if (username.equals( d.getUsername())){
                return d;
            }
        }
        return null;
    }

}