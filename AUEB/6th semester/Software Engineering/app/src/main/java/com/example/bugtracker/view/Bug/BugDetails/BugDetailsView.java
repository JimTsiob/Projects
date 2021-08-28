package com.example.bugtracker.view.Bug.BugDetails;
import com.example.bugtracker.Bug;

import java.util.Date;

public interface BugDetailsView {
    int getAttachedBugID();
    String getAttachedUsername();
    void setBugID(String value);
    void set_text_bug_name(String value);
    void setPriority(Bug.Priority value);
    void setStatus(Bug.bugStatus value);
    void setIssuanceDate(Date d);
    void setDescription(String value);
}
