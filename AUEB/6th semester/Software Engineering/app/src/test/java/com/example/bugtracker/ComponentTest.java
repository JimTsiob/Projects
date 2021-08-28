package com.example.bugtracker;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ComponentTest {

    @Test
    void getCompBugs() throws Exception{
        Component c = new Component("Interface");
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1,dev2, "Pesky little bug!!");
        c.compBugs.add(b);
        assertEquals(c.compBugs,c.getCompBugs());
    }


    @Test
    void getName() {
        Component c = new Component("comp1");
        assertEquals("comp1",c.getName());
    }

   @Test
    void setName() {
       Component c = new Component("comp1");
       c.setName("comp2");
       assertEquals("comp2",c.getName());
    }

    @Test
    void getUid(){
        Component c = new Component("comp1");
        assertEquals(c.counter,c.getUid());
    }

    @Test
    void addBug() throws Exception{
        Component c = new Component("comp1");
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1,dev, "Pesky little bug!!");
        c.addBug(b);
        assertEquals(1,c.compBugs.size());
    }
}