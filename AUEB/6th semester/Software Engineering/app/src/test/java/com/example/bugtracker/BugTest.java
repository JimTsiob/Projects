package com.example.bugtracker;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BugTest {

    // Ola ta setter/show methods testarontai xrhsimopoiwntas ta getter methods.
    // ta exceptions xrhsimopoiountai gia to parse ths Simple Date Format.

    @Test
    void updateBug() throws Exception{
        // to update bug ousiastika xrhsimopoiei ola ta setter methods opote to testaroume me ton exhs tropo.
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev1 = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Developer");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        String date2 = "01/09/2020";
        Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
        b.updateBug("backend problem",Bug.Priority.Low,Bug.bugStatus.ToDo,d2,dev1,"Serious bug in interface");
        assertEquals(b.counter,b.getId());
        assertEquals(Bug.Priority.Low,b.getSeverity());
        assertEquals(Bug.bugStatus.ToDo,b.getStatus());
        assertEquals(d2,b.getIssuanceDate());
        assertEquals("Serious bug in interface",b.getDescription());
    }

    @Test
    void getId() throws Exception{
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev, "Pesky little bug!!");
        assertEquals(b.counter,b.getId());
    }

    @Test
    void getName() throws Exception {
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev, "Pesky little bug!!");
        assertEquals("Interface Problem",b.getName());
    }

    @Test
    void getCreatedBy() throws Exception{
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        assertEquals(dev2,b.getCreatedBy());
    }

    @Test
    void getSeverity() throws Exception{
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        assertEquals(Bug.Priority.High,b.getSeverity());
    }

    @Test
    void getStatus() throws Exception {
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        assertEquals(Bug.bugStatus.InProgress,b.getStatus());
    }

    @Test
    void getIssuanceDate() throws Exception {
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        assertEquals(d1,b.getIssuanceDate());
    }




    @Test
    void getDescription() throws Exception{
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        assertEquals("Pesky little bug!!",b.getDescription());
    }

    @Test
    void setName() throws Exception {
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        b.setName("backend issue");
        assertEquals("backend issue",b.getName());
    }

    @Test
    void setCreatedBy() throws Exception {
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Developer dev3 = new Developer("Panosk","123456789","Panos","Kyr","hoho@gmail.com","Developer");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        b.setCreatedBy(dev3);
        assertEquals(dev3,b.getCreatedBy());
    }

    @Test
    void setSeverity() throws Exception{
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        b.setSeverity(Bug.Priority.Medium);
        assertEquals(Bug.Priority.Medium,b.getSeverity());
    }

    @Test
    void setStatus() throws Exception{
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        b.setStatus(Bug.bugStatus.Done);
        assertEquals(Bug.bugStatus.Done,b.getStatus());
    }

    @Test
    void setIssuanceDate() throws Exception{
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        String date2 = "01/09/2020";
        Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
        b.setIssuanceDate(d2);
        assertEquals(d2,b.getIssuanceDate());
    }



    @Test
    void setDescription() throws Exception {
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        b.setDescription("Uncool bug");
        assertEquals("Uncool bug",b.getDescription());
    }
}