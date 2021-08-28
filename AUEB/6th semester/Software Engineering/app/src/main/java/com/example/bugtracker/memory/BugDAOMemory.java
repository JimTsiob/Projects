package com.example.bugtracker.memory;

import com.example.bugtracker.Bug;
import com.example.bugtracker.dao.BugDAO;

import java.util.ArrayList;
import java.util.List;

public class BugDAOMemory implements BugDAO {
    protected static ArrayList<Bug> entities=new ArrayList<>();

    @Override
    public void delete(Bug entity) {
        entities.remove(entity);
    }

    @Override
    public List<Bug> findAll() {
        ArrayList<Bug> result=new ArrayList<>();
        result.addAll(entities);
        return result;
    }

    @Override
    public void save(Bug entity) {
        entities.add(entity);

    }

    @Override
    public Bug find(int uid) {
       for(Bug e:entities){
           if (e.getId() == uid) return e;
       }
       return  null;
    }


}
