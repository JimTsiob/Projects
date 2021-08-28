package com.example.bugtracker.view.Bug.AssignBug;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bugtracker.R;
import com.example.bugtracker.memory.BugDAOMemory;
import com.example.bugtracker.memory.DeveloperDAOMemory;

public class AssignBugActivity extends AppCompatActivity implements AssignBugView {

    AssignBugPresenter presenter;

    /**
     * The required onCreate method for the Assign Bug interface.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_bug);
         presenter=new AssignBugPresenter(this,new BugDAOMemory(),new DeveloperDAOMemory());


    }

    /**
     * Getter method for usage in the presenter file.
     * Implemented from AssignBugView.
     * @return
     */

    public String getAttachedUsername(){
        return this.getIntent().hasExtra("EXTRA USERNAME") ? this.getIntent().getExtras().getString("EXTRA USERNAME") : null;
    }

    /**
     * Getter method for usage in the presenter file.
     * Implemented from AssignBugView.
     * @return
     */

    public int getAttachedBugID(){
        return this.getIntent().hasExtra("EXTRA_BUG_ID") ? this.getIntent().getExtras().getInt("EXTRA_BUG_ID") : null;
    }

    /**
     * Getter method for usage in the presenter file.
     * Implemented from AssignBugView.
     * @return
     */

    public String getAsignee(){
        return ((EditText)findViewById(R.id.editTextAsigneeDevUsername)).getText().toString();
    }

    /**
     * Finish method that prints a success message to the user.
     */

    public void goodFinish(){
        Toast.makeText(this,"Succesfully Assigned Bug", Toast.LENGTH_LONG);
        finish();
    }

    /**
     * Finish method that prints a failure message to the user.
     */

    public void badFinish(){
        Toast.makeText(this,"Could not Assign Bug reenter dev name", Toast.LENGTH_LONG);
        finish();
    }

    /**
     * Method that's used for assign a bug to a developer.
     * @param view the required view for execution.
     */

    public void onClickAssignBug(View view) {
    presenter.onClickAssign();
    }
}
