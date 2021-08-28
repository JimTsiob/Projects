package com.example.bugtracker.view.Project.ProjectDetails;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugtracker.Project;
import com.example.bugtracker.R;

import java.util.List;

public class ProjectRecViewAdapter extends RecyclerView.Adapter<ProjectRecViewAdapter.ViewHolder> {
    private Context context;
    private List<Project> projects;

    public ProjectRecViewAdapter(Context c){
        context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ProjectRecViewAdapter.ViewHolder holder,int position){
        holder.txtName.setText(projects.get(position).getName());

        holder.parent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("creating intent", "start");
                Intent intent_proj_details = new Intent(context,ProjectDetailsActivity.class);
                Log.d("creating intent ", "end successfull");
                intent_proj_details.putExtra("EXTRA_PROJECT_ID",projects.get(position).getProjectId());
                context.startActivity(intent_proj_details);
            }
        });

    }

    @Override
    public int getItemCount(){
        return projects.size();
    }

    public void setProjectList(List<Project> pl){
        projects = pl;
        notifyDataSetChanged();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private RelativeLayout parent;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtName=itemView.findViewById(R.id.text_project_name);
            parent=itemView.findViewById(R.id.parent2);
        }
    }
}
