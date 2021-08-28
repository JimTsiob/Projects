package com.example.bugtracker;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeveloperTest {

    @Test
    void createBug() throws Exception {
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        assertEquals("Pesky little bug!!",dev.createBug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1,"Pesky little bug!!"));
    }

    @Test
    void updateBug() throws Exception{
        // to update bug ousiastika xrhsimopoiei ola ta setter methods opote to testaroume me ton exhs tropo.
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        String date2 = "01/09/2020";
        Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
        Developer dev3 = new Developer("Hahieod","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        assertEquals("Serious bug in interface",dev.updateBug(b,"backend issue",Bug.Priority.Low,Bug.bugStatus.ToDo,d2,dev2,"Serious bug in interface"));
    }

    @Test
    void editProfile() {
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        dev.editProfile("NickTheQuick","Nick","Quick","haha@gmail.com","Component Owner");
        assertEquals(dev.counter,dev.getId());
        assertEquals("NickTheQuick",dev.getUsername());
        assertEquals("Nick",dev.getFirstName());
        assertEquals("Quick",dev.getLastName());
        assertEquals("haha@gmail.com",dev.getEmail());
        assertEquals("Component Owner",dev.getRole());
    }

    @Test
    void notifyAssignment() throws Exception {
        // edw elegxoume oti ta arraylists periexoun ta swsta projects kai components
        // pou tha vgoun sthn othonh.
        Developer dev = new Developer("NickTheQuick","12345","Nick","Quick","haha@gmail.com","Developer");
        Project p =  new Project("Interface");
        Component c = new Component("style");
        dev.assignedProjects.add(p);
        dev.assignedComps.add(c);
        assertEquals(dev.assignedComps,dev.getAssignedComps());
        assertEquals(dev.assignedProjects,dev.getAssignedProjects());
    }

    @Test
    void assignBug() throws Exception{
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","12345","Nick","Quick","haha@gmail.com","Developer");
        String d = "10/08/2020";
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        assertEquals("Project Owner",dev.assignBug(b,dev2));
    }

    @Test
    void assignComponentToDev(){
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","12345","Nick","Quick","haha@gmail.com","Component Owner");
        Component c1 = new Component("Comp1");
        dev.assignComponentToDev(c1,dev2);
        assertEquals(1,dev2.assignedComps.size());
    }

    @Test
    void addComponentToProject(){
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Component c1 = new Component("Comp1");
        Project p = new Project("Project1");
        dev.addComponentToProject(c1,p);
        assertEquals(1,p.projectComps.size());
    }

    @Test
    void notifyCO() {
        // to idio me th notifyAssignment gia Component Owners.
        Developer pdev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Component c1 = new Component("interface");
        Component c2 = new Component("backend");
        Project p = new Project ("bug tracker software");
        Developer dev = new Developer("NickTheQuick","12345","Nick","Quick","haha@gmail.com","Component Owner");
        dev.assignedProjects.add(p);
        dev.assignedComps.add(c1);
        dev.assignedComps.add(c2);
        assertEquals("Project Owner",pdev.notifyCO(dev));
    }

    @Test
    void changePassword(){
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        dev.changePassword("hehe12");
        assertEquals("hehe12",dev.getPassword());
    }

    @Test
    void createProfile(){
        // edw efoson gyrnaei Developer object kanoume ton parakatw elegxo.
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        assertEquals("Jimbo2000",dev.createProfile("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner"));
    }

    @Test
    void setUsername(){
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        dev.setUsername("Jimakos2000");
        assertEquals("Jimakos2000",dev.getUsername());
    }

    @Test
    void setPassword(){
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        dev.setPassword("345");
        assertEquals("345",dev.getPassword());
    }

    @Test
    void setFirstName() {
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        dev.setFirstName("Mike");
        assertEquals("Mike",dev.getFirstName());
    }

    @Test
    void setLastName() {
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        dev.setLastName("Dean");
        assertEquals("Dean",dev.getLastName());
    }

    @Test
    void setEmail() {
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        dev.setEmail("hoho@gmail.com");
        assertEquals("hoho@gmail.com",dev.getEmail());
    }

    @Test
    void setRole() {
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        dev.setRole("Developer");
        assertEquals("Developer",dev.getRole());
    }

    @Test
    void getId() {
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        assertEquals(dev.counter,dev.getId());
    }

    @Test
    void getAssignedBugs() throws Exception{
        String d = "10/08/2020";
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Developer dev2 = new Developer("NickTheQuick","123456789","Nick","Quick","haha@gmail.com","Component Owner");
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Bug b =  new Bug("Interface Problem",Bug.Priority.High,Bug.bugStatus.InProgress,d1, dev2, "Pesky little bug!!");
        dev.assignedBugs.add(b);
        assertEquals(dev.assignedBugs,dev.getAssignedBugs());
    }

    @Test
    void getAssignedComps() {
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Component c = new Component("Interface");
        dev.assignedComps.add(c);
        assertEquals(dev.assignedComps,dev.getAssignedComps());
    }

    @Test
    void getAssignedProjects() {
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        Project p = new Project("BugTracker");
        dev.assignedProjects.add(p);
        assertEquals(dev.assignedProjects,dev.getAssignedProjects());
    }

    @Test
    void getUsername() {
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        assertEquals("Jimbo2000",dev.getUsername());
    }

    @Test
    void getFirstName() {
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        assertEquals("Jim",dev.getFirstName());
    }

    @Test
    void getPassword(){
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        assertEquals("123456789",dev.getPassword());
    }

    @Test
    void getLastName() {
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        assertEquals("Beam",dev.getLastName());
    }

    @Test
    void getEmail() {
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        assertEquals("hihi@gmail.com",dev.getEmail());
    }

    @Test
    void getRole() {
        Developer dev = new Developer("Jimbo2000","123456789","Jim","Beam","hihi@gmail.com","Project Owner");
        assertEquals("Project Owner",dev.getRole());
    }
}