package com.example.bugtracker.memory;

import com.example.bugtracker.Component;
import com.example.bugtracker.dao.ComponentDAO;

import java.util.ArrayList;
import java.util.List;


public class ComponentDAOMemory implements ComponentDAO{
    protected static ArrayList<Component> data = new ArrayList<>();

    @Override
    public void delete(Component comp){
        for (Component c : data){
            if (c.getName() == comp.getName()){
                data.remove(comp);
            }
        }
    }

    @Override
    public List<Component> findAll(){
        return data;
    }

    @Override
    public void save(Component comp){
        data.add(comp);
    }

    /*
    public Component find(String name){
        for (Component c : data){
            if (name == c.getName()){
                return c;
            }
        }
        return null;
    }*/

    public Component find(int UID){
        for(Component c:data ){
            if(Integer.valueOf(c.getUid()).equals(Integer.valueOf(UID))) return c;
        }
    return  null;}

}