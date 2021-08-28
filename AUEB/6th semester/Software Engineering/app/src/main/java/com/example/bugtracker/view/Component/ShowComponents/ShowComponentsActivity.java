package com.example.bugtracker.view.Component.ShowComponents;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugtracker.R;
import com.example.bugtracker.memory.ComponentDAOMemory;
import com.example.bugtracker.memory.DeveloperDAOMemory;

public class ShowComponentsActivity extends AppCompatActivity implements ShowComponentsView {

    private RecyclerView ComponentsRecView;
    private ShowComponentsPresenter presenter;
    private static boolean daoDataIsInitialised=false;


    /**
     * The required onCreate method used for the Show Components interface.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initialize dao objects
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_components);


        //initialize the activity presenter
        presenter = new ShowComponentsPresenter(this, new ComponentDAOMemory(), new DeveloperDAOMemory());

        //bind recyclerView to the element in the layout file
        ComponentsRecView = findViewById(R.id.ComponentsRecView);

        //intitialise adapter and pass context so it can open other activities
        ComponentsRecViewAdapter myadapter = new ComponentsRecViewAdapter(this);

        //take the data from the presenter and give them to the adapter to display
        myadapter.setComponentList(presenter.onLoadSource());

        //connect the recycler view with the adapter that has the data now
        ComponentsRecView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();

        //display in linear fashion
        ComponentsRecView.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * Getter method used by the presenter file.
     * @return the dev's username.
     */

    public String getAttachedUserName(){
        return this.getIntent().hasExtra("EXTRA USERNAME") ? this.getIntent().getExtras().getString("EXTRA USERNAME") : null;
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



