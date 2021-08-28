package com.example.bugtracker.dao;

import com.example.bugtracker.Developer;

import java.util.List;


public interface DeveloperDAO{
    void delete(Developer dev);

    List<Developer> findAll();

    void save(Developer comp);

    Developer find(String username);
}