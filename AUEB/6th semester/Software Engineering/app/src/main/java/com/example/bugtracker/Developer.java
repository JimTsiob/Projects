package com.example.bugtracker;
import java.util.Date;
import java.util.ArrayList;

public class Developer {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String Role;
    public static int counter = 0; // tha xrhsimopoihthei gia na exoume distinct id.

    public ArrayList<Bug> assignedBugs; // exyphretei th sysxetish apo Dev se bug , posa bugs tou exoun anatethei.

    public ArrayList<Component> assignedComps; // sysxetish m..n dev component , se posa components douleyei o Dev.

    public ArrayList<Project> assignedProjects; // sysxetish m..n dev project, se posa projects douleuei o Dev.

    public Developer(String u, String p, String fn, String ln, String e, String r){
        id = ++counter;
        username = u;
        password = p;
        firstName = fn;
        lastName = ln;
        email = e;
        Role = r;
        assignedBugs = new ArrayList<>();
        assignedComps = new ArrayList<>();
        assignedProjects = new ArrayList<>();
    }

    /**
     * method to create bugs
     * @param n bug name
     * @param sev bug priority
     * @param status bug status
     * @param isd bug date
     * @param desc bug description
     * @return check for tests.
     */

    public String createBug(String n,Bug.Priority sev, Bug.bugStatus status, Date isd,  String desc){

        Bug b = new Bug(n,sev,status,isd, this, desc);
        String check = b.getDescription();
        // oi methodoi epistrefoun ena string
        // wste na to elegxoume gia ta coverage reports.
        // edw elegxoume thn perigrafh tou bug
        // wste na doume an dhmiourghthike swsta.
        return check;
    }

    /**
     * method to update bugs.
     * @param b the bug to be changed.
     * @param n bug name
     * @param sev bug priority
     * @param status bug status
     * @param isd bug date
     * @param cb bug creator
     * @param desc bug description
     * @return a string for check in the Test.
     */

    public String updateBug(Bug b,String n, Bug.Priority sev, Bug.bugStatus status, Date isd,Developer cb, String desc){
        b.updateBug(n,sev,status,isd,cb,desc);
        String check = b.getDescription();
        return check;
    }

    /**
     * method used to edit a user's profile
     * @param u new username
     * @param fn new firstname
     * @param ln new lastname
     * @param e new email
     * @param r new role
     */

    public void editProfile(String u,String fn, String ln, String e, String r){
        this.setUsername(u);
        this.setFirstName(fn);
        this.setLastName(ln);
        this.setEmail(e);
        this.setRole(r);
    }

    /**
     * method used to assign components to COs. (Only the PO can use this)
     * @param component component to be assigned.
     * @param developer the CO
     */

    public void assignComponentToDev(Component component, Developer developer){
        if (this.Role.equals("Project Owner") ) {
            developer.assignedComps.add(component);
        }

    }

    /**
     * method used to add new components to the project.
     * @param component the component to be added
     * @param project the project that will receive the new component.
     */

    public void addComponentToProject(Component component,Project project)
    {
        if(this.Role.equals("Project Owner")) {project.addComponent(component);}
    }

    /**
     * method used to assign bugs
     * @param b the bug
     * @param dev the developer to be assigned the bug
     * @return string for check in Test.
     */

    public String assignBug(Bug b,Developer dev){
        if (this.Role.equals("Component Owner") || this.Role.equals("Project Owner")){
            dev.assignedBugs.add(b);
            System.out.println("Bug " + b.getId() + " has been assigned to developer " + dev.getUsername());
        }else{
            System.out.println("You're not a Project Owner or Component Owner, thus , you can't use this function.");
        }
        String check = this.getRole();
        return check;
    }

    /**
     * message method for CO notification
     * @param dev the CO
     * @return checker string for test
     */

    public String notifyCO(Developer dev){
        if (this.Role.equals("Project Owner")){
            dev.notifyAssignment();
        }else{
            System.out.println("You're not a Project Owner ,therefore you can't use this function.");
        }
        String check = this.getRole();
        return check;
    }

    /**
     * method for message showing of new bug assignment.
     */

    public void notifyAssignment(){
        System.out.println("You've been assigned to the following Projects: ");
        for (Project p : assignedProjects){
            System.out.println("Project Name : " + p.getName());
        }
        System.out.println("You've been assigned to the following Components and their respective bugs: ");
        for (Component c : assignedComps){
            System.out.println("Component Name : " + c.getName());
        }
    }

    /**
     * setter for password changing.
     * @param p the new password.
     */

    public void changePassword(String p){
        this.setPassword(p);
    }

    public String createProfile(String u, String p, String fn, String ln, String e, String r){
        Developer newacc = new Developer(u,p,fn,ln,e,r);
        String userCheck = newacc.getUsername();
        return userCheck;
    }

    /**
     * setter method
     * @param u new username
     */

    public void setUsername(String u){
        username = u;
    }

    /**
     * setter method
     * @param p new password
     */

    public void setPassword(String p){
        password = p;
    }

    /**
     * setter method
     * @param fn new firstname
     */

    public void setFirstName(String fn){
        firstName = fn;
    }

    /**
     * setter method
     * @param ln new lastname
     */

    public void setLastName(String ln){
        lastName = ln;
    }

    /**
     * setter method
     * @param e new email
     */

    public void setEmail(String e){
        email = e;
    }

    /**
     * setter method
     * @param r new role
     */

    public void setRole(String r){
        Role = r;
    }

    /**
     * getter method
     * @return dev id
     */

    public int getId(){
        return id;
    }

    /**
     * getter method
     * @return the dev's assigned bugs list.
     */

    public ArrayList<Bug> getAssignedBugs(){
        return assignedBugs;
    }

    /**
     * getter method
     * @return the CO's assigned components list.
     */

    public ArrayList<Component> getAssignedComps(){
        return assignedComps;
    }

    /**
     * getter method
     * @return PO's project list.
     */

    public ArrayList<Project> getAssignedProjects(){
        return assignedProjects;
    }

    /**
     * getter method
     * @return user's password
     */

    public String getPassword() {return password;}

    /**
     * getter method
     * @return user's username
     */

    public String getUsername(){
        return username;
    }

    /**
     * getter method
     * @return user's firstname
     */

    public String getFirstName(){
        return firstName;
    }

    /**
     * getter method
     * @return user's lastname
     */

    public String getLastName(){
        return lastName;
    }

    /**
     * getter method
     * @return user's email
     */

    public String getEmail(){
        return email;
    }

    /**
     * getter method
     * @return user's role
     */

    public String getRole(){
        return Role;
    }
}
