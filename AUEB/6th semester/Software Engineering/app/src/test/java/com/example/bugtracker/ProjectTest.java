package com.example.bugtracker;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void getName() {
        Project p = new Project("cool");
        assertEquals("cool",p.getName());
    }

    @Test
    void getProjectId() {
        Project p = new Project("cool");
        assertEquals(p.counter,p.getProjectId());
    }

    @Test
    void showProjectDetails() {
        Project p = new Project("cool");
        assertEquals(p.counter,p.getProjectId());
        assertEquals("cool",p.getName());
    }

    @Test
    void setName() {
        Project p = new Project("cool");
        p.setName("not cool");
        assertEquals("not cool",p.getName());
    }

    @Test
    void getTickets() throws Exception {
        Project p = new Project("cool");
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1,dev2, "Pesky little bug!!");
        p.tickets.add(b);
        assertEquals(p.tickets,p.getTickets());
    }

    @Test
    void getComps() {
        Project p = new Project("cool");
        Component c1 = new Component("interface");
        Component c2 = new Component("backend");
        p.projectComps.add(c1);
        p.projectComps.add(c2);
        assertEquals(p.projectComps,p.getComps());
    }

    @Test
    void getDevs() {
        Project p = new Project("cool");
        Developer d1 = new Developer("Jim2000","123456789","Jim","Tsiob","hihi@gmail.com","Project Owner");
        Developer d2 = new Developer("SJ2000","123456789","Samuel","Jackson","hoho@gmail.com","Component Owner");
        p.users.add(d1);
        p.users.add(d2);
        assertEquals(p.users,p.getDevs());
    }
}