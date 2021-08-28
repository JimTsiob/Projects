package com.example.bugtracker.view.Project.ProjectDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bugtracker.Project;
import com.example.bugtracker.R;
import com.example.bugtracker.memory.ProjectDAOMemory;
import com.example.bugtracker.view.Project.AddEditProject.AddEditProjectActivity;

public class ProjectDetailsActivity extends AppCompatActivity implements ProjectDetailsView{
    private ProjectDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        presenter = new ProjectDetailsPresenter(this,new ProjectDAOMemory());
        Toast.makeText(this,Integer.toString(this.getAttachedProjectId()) + "Project selected", Toast.LENGTH_SHORT);
    }


    public int getAttachedProjectId(){
        return this.getIntent().hasExtra("EXTRA_PROJECT_ID") ? this.getIntent().getExtras().getInt("EXTRA_PROJECT_ID") : null;
    }

    // gia allagh onomatos tou project. Mono ayto mporoume na allaxoume.
    public void setTextProjectName(String value){
        ((TextView) findViewById(R.id.proj_name)).setText(value);
    }
}
