package com.example.bugtracker.view.Bug.ShowBugs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugtracker.R;
import com.example.bugtracker.memory.BugDAOMemory;
import com.example.bugtracker.memory.DeveloperDAOMemory;
import com.example.bugtracker.view.Bug.AddEditBug.AddEditBugActivity;

public class ShowBugsActivity extends AppCompatActivity {

    private RecyclerView bugsRecView;
    private ShowBugsPresenter presenter;
    //private static boolean daoDataIsInitialised=false;


    /**
     * The required onCreate method used for the Show bugs interface.
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initialize dao objects
        /**
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
        setContentView(R.layout.activity_main);




        //initialize the activity presenter
        presenter =new ShowBugsPresenter(this,new BugDAOMemory(),new DeveloperDAOMemory());

        //bind recyclerView to the element in the layout file
        bugsRecView=findViewById(R.id.BugRecView);

        //intitialise adapter and pass context so it can open other activities
        BugsRecViewAdapter myadapter=new BugsRecViewAdapter(this);

        //take the data from the presenter and give them to the adapter to display
        myadapter.setBugslist(presenter.onLoadSource());

        //connect the recycler view with the adapter that has the data now
        bugsRecView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();

        //display in linear fashion
        bugsRecView.setLayoutManager(new LinearLayoutManager(this));

        //btn to create new bug
        Button btn_create_bug=findViewById(R.id.btn_create_bug);


    }

    /**
     * Method used when the CREATE BUG button is used to take the user
     * to the add edit bug interface.
     * @param view
     */

    public void onCreateBug(View view){
        Intent intent=new Intent(this, AddEditBugActivity.class);
        startActivity(intent);


    }

    /**
     * Getter method used by the presenter.
     * @return the dev's username.
     */

    public String getAttachedUsername(){
        return this.getIntent().hasExtra("EXTRA USERNAME")? this.getIntent().getExtras().getString("EXTRA USERNAME") : null;
    }

}


        /*
        * previous listview replaced by recycler view
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *

        itemListView=findViewById(R.id.item_list_view);
        ArrayAdapter<Bug> buginfoAdapter=new ArrayAdapter<>
                (
                this, android.R.layout.simple_list_item_1,presenter.onLoadSource()
        );

        itemListView.setAdapter(buginfoAdapter);


        //handle click events on bugs
      itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Intent bugDetails_intent=new Intent(ShowBugsActivity.this, BugDetailsActivity.class);
              //pass the bug id of the clicked item to the other activity.we use the presenter MVP model
              bugDetails_intent.putExtra("EXTRA_BUG_ID", presenter.onLoadSource().get(i).getId());
              startActivity(bugDetails_intent);
          }
      });*/



