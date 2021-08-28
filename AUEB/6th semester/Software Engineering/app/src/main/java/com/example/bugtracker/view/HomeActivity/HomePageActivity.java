package com.example.bugtracker.view.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bugtracker.R;
import com.example.bugtracker.memory.DeveloperDAOMemory;
import com.example.bugtracker.view.Bug.ShowBugs.ShowBugsActivity;
import com.example.bugtracker.view.Component.ShowComponents.ShowComponentsActivity;
import com.example.bugtracker.view.Project.ProjectDetails.ProjectDetailsActivity;

public class HomePageActivity extends AppCompatActivity implements HomePageView {
//private static  boolean daoDataIsInitialised=false;


    /**
     * Required onCreate method for the Home page interface.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

/*
        if(!daoDataIsInitialised)
        {
            try {
                new Initializer().preparedata();
                daoDataIsInitialised=true;
            }
            catch (ParseException e) {
                e.printStackTrace(); }
        }
*/



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        final HomePagePresenter presenter=new HomePagePresenter(this  ,new DeveloperDAOMemory());
        findViewById(R.id.go_to_Bugs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickManageBugs();
            }
        });
        findViewById(R.id.go_to_Components).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickManageComponents();
            }
        });
        findViewById(R.id.go_to_Projects).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickManageProjects();
            }
        });

    }

    /**
     * method used to take the user to the bugs list.
     */

    @Override
    public void manageBugs() {
        Intent intent_bugs=new Intent(this, ShowBugsActivity.class);
        //pass the username for showing the correct bugs in the view
        //Project owner sees all bugs
        //Component Owner bugs of his component
        //developer bugs that are assigned to him
        intent_bugs.putExtra("EXTRA USERNAME",getAttachedUsername());
        startActivity(intent_bugs);

    }

    @Override
    public void mangeProfile() {

    }

    /**
     * getter method used by the presenter.
     * @return dev's username
     */

    @Override
    public String getAttachedUsername(){
       Log.d("*** getAttUName", this.getIntent().getExtras().getString("EXTRA_USERNAME") );
        return this.getIntent().hasExtra("EXTRA_USERNAME") ? this.getIntent().getExtras().getString("EXTRA_USERNAME") : null;

    }

    /**
     * method used to take the user to the components list.
     */

    public void manageComponents(){
        Intent intent_components=new Intent(this, ShowComponentsActivity.class);
        intent_components.putExtra("EXTRA USERNAME",getAttachedUsername());
        startActivity(intent_components);

    }

    /**
     * method used to show the project to the user.
     */

    public void manageProjects(){
        Intent intent_projects=new Intent(this, ProjectDetailsActivity.class);
        startActivity(intent_projects);
    }

    //set the text to the username

    /**
     * Welcome message
     * @param value the message
     */

    public void setUsername(String value){
        value="hi, "+ value;
        ((TextView) findViewById(R.id.username_in_home_ui)).setText(value);
    }
}













