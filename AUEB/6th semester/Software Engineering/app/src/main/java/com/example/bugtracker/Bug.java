package com.example.bugtracker;
import java.util.Date;
public class Bug {

    public enum bugStatus{
        ToDo,InProgress,Done;
    }

    public enum Priority{
        High,Medium,Low;
    }

    private int id;
    public static int counter = 0;
    private String name;
    private Priority severity;
    private bugStatus status;
    private Date issuanceDate;
    private Developer createdBy;
    private String description;

   public Bug(String n, Priority sev, bugStatus stat, Date issd,  Developer cb, String desc){
        createdBy = cb;
        id = ++counter;
        name = n;
        severity = sev;
        status = stat;
        issuanceDate = issd;
        description = desc;
    }

    /**
     * Method that updates a bug.
     * @param n new name
     * @param sev new priority
     * @param stat new status
     * @param issd new date
     * @param cb new creator Developer(the one that updated it)
     * @param desc new description
     */

    public void updateBug(String n,Priority sev,bugStatus stat, Date issd,Developer cb, String desc){
        this.setDescription(desc);
        this.setIssuanceDate(issd);
        this.setSeverity(sev);
        this.setStatus(stat);
        this.setName(n);
        this.setCreatedBy(cb);
    }

    /**
     * Getter method
     * @return id
     */

    public int getId(){
        return id;
    }

    /**
     * Getter method
     * @return bug name
     */

    public String getName(){
        return name;
    }

    /**
     * Getter method
     * @return bug priority
     */

    public Priority getSeverity(){
        return severity;
    }

    /**
     * Getter method
     * @return bug status
     */

    public bugStatus getStatus(){
        return status;
    }

    /**
     * Getter method
     * @return bug date
     */

    public Date getIssuanceDate(){
        return issuanceDate;
    }

    /**
     * Getter method
     * @return bug description
     */

    public String getDescription(){
        return description;
    }

    /**
     * Getter method
     * @return bug creator
     */

    public Developer getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter method
     * @param n new name
     */

    public void setName(String n){ name = n; }

    /**
     * Setter method
     * @param sev new priority
     */

    public void setSeverity(Priority sev){
        severity = sev;
    }

    /**
     * Setter method
     * @param stat new status
     */

    public void setStatus(bugStatus stat){
        status = stat;
    }

    /**
     * Setter method
     * @param issd new date
     */

    public void setIssuanceDate(Date issd){
        issuanceDate = issd;
    }

    /**
     * Setter method
     * @param desc new description
     */

    public void setDescription(String desc){
        description = desc;
    }

    /**
     * Setter method
     * @param createdBy new creator (the dev who updated)
     */

    public void setCreatedBy(Developer createdBy) {
        this.createdBy = createdBy;
    }
}
