package com.example.bugtracker.dao;

import com.example.bugtracker.Project;

import java.util.List;

public interface ProjectDAO{
    void delete(Project p);

    List<Project> findAll();

    void save(Project p);

    Project find(int id);
}