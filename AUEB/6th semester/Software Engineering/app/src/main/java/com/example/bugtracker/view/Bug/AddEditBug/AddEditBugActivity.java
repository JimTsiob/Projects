package com.example.bugtracker.view.Bug.AddEditBug;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bugtracker.Bug;
import com.example.bugtracker.R;
import com.example.bugtracker.memory.BugDAOMemory;
import com.example.bugtracker.memory.DeveloperDAOMemory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEditBugActivity extends AppCompatActivity implements AddEditBugView {

    /**
     * The required interface for the add / edit screen.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_bug);
        //final to save the changes after returning
        final AddEditBugPresenter presenter=new AddEditBugPresenter(this,new BugDAOMemory(),new DeveloperDAOMemory());
        findViewById(R.id.btn_save_bug).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    presenter.onSaveBug();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        /*findViewById(R.id.btn_assign_bug).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try{
                    presenter.onAssignBug();
                }catch(ParseException e){
                    e.printStackTrace();
                }
            }
        }
        );*/
    }

    /**
     * getter method for presenter.
     * implemented from AddEditBugView.
     * @return
     */

    @Override
    public Integer getAttachedBugID() {
        return this.getIntent().hasExtra("EXTRA_BUG_ID") ? this.getIntent().getExtras().getInt("EXTRA_BUG_ID") : null;
    }

    /**
     * getter method for presenter.
     * implemented from AddEditBugView.
     * @return
     */

    //returns the name that we put in the bug name
    @Override
    public String getBugName() {

        return ((EditText)findViewById(R.id.edit_bug_name)).getText().toString();
    }

    /**
     * getter method for presenter.
     * implemented from AddEditBugView.
     * @return
     */

    @Override
    public Bug.Priority getBugPriority(){
        String conv =  ((EditText)findViewById(R.id.edit_bug_priority)).getText().toString();
        Bug.Priority prio;
        if (conv.equalsIgnoreCase("High")){
            prio = Bug.Priority.High;
            return prio;
        }else if (conv.equalsIgnoreCase("Medium")){
            prio = Bug.Priority.Medium;
            return prio;
        }else{
            prio = Bug.Priority.Low;
            return prio;
        }

    }

    /**
     * getter method for presenter.
     * implemented from AddEditBugView.
     * @return
     */

    @Override
    public Bug.bugStatus getStatus(){
        String conv =  ((EditText) findViewById(R.id.edit_bug_status)).getText().toString();
        Bug.bugStatus stat;
        if (conv.equalsIgnoreCase("Done")){
            stat = Bug.bugStatus.Done;
            return stat;
        }else if (conv.equalsIgnoreCase("InProgress")){
            stat = Bug.bugStatus.InProgress;
            return stat;
        }else{
            stat = Bug.bugStatus.ToDo;
            return stat;
        }
    }

    /**
     * getter method for presenter.
     * implemented from AddEditBugView.
     * @return
     */

    @Override
    public Date getBugDate() throws Exception{
        String conv = ((EditText) findViewById(R.id.edit_bug_date)).getText().toString();
        Date d = new SimpleDateFormat("dd/MM/yyyy").parse(conv);
        return d;
    }

    /**
     * getter method for presenter.
     * implemented from AddEditBugView.
     * @return
     */

    @Override
    public String getBugDescription(){
        return ((EditText)findViewById(R.id.edit_bug_desc)).getText().toString();
    }

    @Override
    public void setBugName(String name) {

    }


    /**
     * Finish method that returns a message to the user.
     * @param msg the message to be returned.
     */

    @Override
    public void goodFinish(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
       // Intent intent=new Intent(this, ShowBugsActivity.class);
        //startActivity(intent);
        finish();
    }

    /**
     * method to handle errors in the activity.
     * @param msg the error type.
     */

    @Override
    public void showError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this,AddEditBugActivity.class);
       // startActivity(intent);
        // mas xanabgazei pali sthn idia selida.
    }
}