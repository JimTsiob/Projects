package com.example.bugtracker.view.Bug.BugDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bugtracker.Bug;
import com.example.bugtracker.R;
import com.example.bugtracker.memory.BugDAOMemory;
import com.example.bugtracker.memory.DeveloperDAOMemory;
import com.example.bugtracker.view.Bug.AddEditBug.AddEditBugActivity;
import com.example.bugtracker.view.Bug.AssignBug.AssignBugActivity;

import java.util.Date;

public class BugDetailsActivity extends AppCompatActivity implements BugDetailsView {
private BugDetailsPresenter presenter;

    /**
     * the required onCreate method for the Bug Details interface.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_details);
        presenter=new BugDetailsPresenter(this,new BugDAOMemory(),new DeveloperDAOMemory());
        Toast.makeText(this, Integer.toString(this.getAttachedBugID())+" BUG SELECTED", Toast.LENGTH_SHORT);

    }

    /**
     * Method used on click (by the EDIT button) and takes the user to the Add Edit Bug interface.
     * @param view
     */

    //pass view to start intent
    public void onEditBug(View view){
        Intent intent_edit_bug=new Intent(this, AddEditBugActivity.class);
        intent_edit_bug.putExtra("EXTRA_BUG_ID",this.getAttachedBugID());
        startActivity(intent_edit_bug);
    }

    /**
     * Method used to take the user to the Assign Bug interface.
     * If the user is a Developer , a failure message will appear on the screen
     * as a developer cannot assign bugs to others.
     * @param view
     */

    public void onAssignBug(View view){
        if(presenter.checkRole())
        {
        Intent intent_assign_bug=new Intent(this, AssignBugActivity.class);
        intent_assign_bug.putExtra("EXTRA_BUG_ID",getAttachedBugID());
        intent_assign_bug.putExtra("EXTRA USERNAME",getAttachedUsername());
        startActivity(intent_assign_bug);}
        else{

            Toast.makeText(this,"You cannot assign a bug", Toast.LENGTH_LONG);
        }
    }


    /**
     * Getter method used in the presenter file
     * Implemented for BugDetailsView.
     * @return
     */

    //the activity of bug details gets the intent from the last activity along with the bug id
    public int getAttachedBugID()
    {
        return this.getIntent().hasExtra("EXTRA_BUG_ID") ? this.getIntent().getExtras().getInt("EXTRA_BUG_ID") : null;
    }

    /**
     * Getter method used in the presenter file
     * Implemented for BugDetailsView.
     * @return
     */

    public String getAttachedUsername() {
        return this.getIntent().hasExtra("EXTRA USERNAME") ? this.getIntent().getExtras().getString("EXTRA USERNAME") : null;
    }

    /**
     * Setter method used for taking inputs from the user.
     * @param value the ID.
     */

    //set the 2 text views to their appropriate values
    public void setBugID(String value){
        ((TextView)findViewById(R.id.text_bug_id)).setText(value);
    }

    /**
     * Setter method used for taking inputs from the user.
     * @param value the bug's name
     */

    public void set_text_bug_name(String value){
        ((TextView)findViewById(R.id.text_bug_name_details)).setText(value);
    }

    /**
     * Setter method used for taking inputs from the user.
     * @param value bug priority
     */

    public void setPriority(Bug.Priority value){
        ((TextView) findViewById(R.id.text_bug_priority_details)).setText(value.toString());
    }

    /**
     * Setter method used for taking inputs from the user.
     * @param value bug status.
     */

    public void setStatus(Bug.bugStatus value){
        ((TextView) findViewById(R.id.text_bug_status_details)).setText(value.toString());
    }

    /**
     * Setter method used for taking inputs from the user.
     * @param d bug date
     */

    public void setIssuanceDate(Date d){
        ((TextView) findViewById(R.id.text_bug_date_details)).setText(d.toString());
    }

    /**
     * Setter method used for taking inputs from the user.
     * @param value bug description
     */

    public void setDescription(String value){
        ((TextView) findViewById(R.id.text_bug_description_details)).setText(value);
    }
}