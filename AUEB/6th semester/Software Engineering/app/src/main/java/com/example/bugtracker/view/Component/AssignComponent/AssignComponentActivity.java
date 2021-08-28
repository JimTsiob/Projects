package com.example.bugtracker.view.Component.AssignComponent;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.bugtracker.R;
import com.example.bugtracker.memory.DeveloperDAOMemory;
import com.example.bugtracker.memory.ComponentDAOMemory;

public class AssignComponentActivity extends AppCompatActivity implements AssignComponentView {
    AssignComponentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_component);
        presenter = new AssignComponentPresenter(this,new ComponentDAOMemory(),new DeveloperDAOMemory());
    }

    public String getAttachedUsername(){
        return this.getIntent().hasExtra("EXTRA USERNAME") ? this.getIntent().getExtras().getString("EXTRA USERNAME") : null;
    }

    public int getAttachedCompID(){
        return this.getIntent().hasExtra("EXTRA_COMPONENT_ID") ? this.getIntent().getExtras().getInt("EXTRA_COMPONENT_ID") : null;
    }

    public String getCOAssignee(){
        return ((EditText) findViewById(R.id.assign_comp)).getText().toString();
    }

    public void goodFinish(){
        Toast.makeText(this,"Successfully Assigned Component", Toast.LENGTH_LONG);
        finish();
    }

    public void badFinish(){
        Toast.makeText(this,"Failed to assign component , re enter CO",Toast.LENGTH_LONG);
        finish();
    }

    public void wrongRoleFinish(){
        Toast.makeText(this,"User is not a Component owner, enter a CO",Toast.LENGTH_LONG);
        finish();
    }

    public void onClickAssignComp(View view){
        presenter.onClickAssignCom();
    }
}
