package com.example.bugtracker.view.Project.AddEditProject;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bugtracker.R;
import com.example.bugtracker.memory.ProjectDAOMemory;

import java.text.ParseException;

public class AddEditProjectActivity extends AppCompatActivity implements AddEditProjectView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_project);

        final AddEditProjectPresenter presenter = new AddEditProjectPresenter(this,new ProjectDAOMemory());
        findViewById(R.id.btn_save_proj).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try{
                    presenter.onSaveProject();
                }catch(ParseException e){
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public Integer getProjectId(){
        return this.getIntent().hasExtra("EXTRA_PROJECT_ID") ? this.getIntent().getExtras().getInt("EXTRA_PROJECT_ID") : null;
    }

    // return result of input.
    @Override
    public String getProjectName() {
        return ((EditText) findViewById(R.id.editProjectName)).getText().toString();
    }


    @Override
    public void goodFinish(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
