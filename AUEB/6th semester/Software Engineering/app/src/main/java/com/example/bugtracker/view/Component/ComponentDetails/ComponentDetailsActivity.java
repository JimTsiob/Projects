package com.example.bugtracker.view.Component.ComponentDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bugtracker.R;
import com.example.bugtracker.memory.ComponentDAOMemory;
import com.example.bugtracker.view.Component.AddEditComponent.AddEditComponentActivity;
import com.example.bugtracker.view.Component.AssignComponent.AssignComponentActivity;

public class ComponentDetailsActivity extends AppCompatActivity implements ComponentDetailsView {

    private ComponentDetailsPresenter presenter;

    /**
     * Required onCreate method for the COmponent details interface.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component_details);
        presenter = new ComponentDetailsPresenter(this,new ComponentDAOMemory());
        Toast.makeText(this,this.getAttachedComponentName() + " component selected",Toast.LENGTH_SHORT);
    }

    /**
     * Method used by the EDIT button to take the user to the Edit component page.
     * @param view the view that's needed for the methods.
     */

    public void onEditComp(View view){
        Intent intent_edit_comp = new Intent(this,AddEditComponentActivity.class);
        intent_edit_comp.putExtra("EXTRA_COMPONENT_ID",this.getAttachedComponentID());
        startActivity(intent_edit_comp);
    }

    public void onAssignComp(View view){
        Intent intent_assign_comp = new Intent(this,AssignComponentActivity.class);
        intent_assign_comp.putExtra("EXTRA_COMPONENT_ID",this.getAttachedComponentID());
        intent_assign_comp.putExtra("EXTRA USERNAME",this.getAttachedUsername());
        startActivity(intent_assign_comp);
    }

    /**
     * Getter method used for the presenter file.
     * @return the developer's username.
     */

    public String getAttachedUsername(){
        return this.getIntent().hasExtra("EXTRA USERNAME") ? this.getIntent().getExtras().getString("EXTRA USERNAME") : null;
    }

    /**
     * Getter method used for the presenter file.
     * @return the component's ID.
     */

    //through the component id we find all the other component info
    @Override
    public Integer getAttachedComponentID() {
        return this.getIntent().hasExtra("EXTRA_COMPONENT_ID") ? this.getIntent().getExtras().getInt("EXTRA_COMPONENT_ID") : null;
    }

    /**
     * Getter method used for the presenter file.
     * @return the component's name.
     */

    public String getAttachedComponentName(){
        return presenter.getAttachedComponentName();
    }

    /**
     * Setter method used for the presenter file.
     * takes input from the user for editing / creating.
     * @param value the new name provided by the user.
     */

    @Override
    public void setCompName(String value){
        ((TextView) findViewById(R.id.text_comp_name_details)).setText(value);
    }


}
