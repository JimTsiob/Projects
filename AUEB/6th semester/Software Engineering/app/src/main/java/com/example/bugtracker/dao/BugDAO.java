package com.example.bugtracker.dao;

import com.example.bugtracker.Bug;

import java.util.List;

public  interface BugDAO {
    void delete(Bug entity);

    List<Bug> findAll();

    void save(Bug entity);

    Bug find(int id);



}
