package com.example.bugtracker.dao;

import com.example.bugtracker.Component;

import java.util.List;


public interface ComponentDAO{
    void delete(Component comp);

    List<Component> findAll();

    void save(Component comp);

    Component find(int uid);
}