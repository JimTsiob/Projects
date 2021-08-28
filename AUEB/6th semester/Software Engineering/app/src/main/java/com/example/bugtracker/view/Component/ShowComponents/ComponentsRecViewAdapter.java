package com.example.bugtracker.view.Component.ShowComponents;

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

import com.example.bugtracker.Component;
import com.example.bugtracker.R;
import com.example.bugtracker.view.Component.ComponentDetails.ComponentDetailsActivity;

import java.util.List;

public class ComponentsRecViewAdapter extends RecyclerView.Adapter<ComponentsRecViewAdapter.ViewHolder> {

    private Context context;
    private List<Component> componentList;

    //since the adapter is outside the activity we need to know the parent activity
    //we pass the context-parent activity in the constructor to be able to generate intents etc
    public ComponentsRecViewAdapter(Context context){this.context=context;}


    @Override
    //creates instance of viewholder class  for every item in our recview
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recview_component_list_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        holder.txtName.setText(componentList.get(position).getName());
        //click on parent so we click on padding too.
        //create click listener
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("creating intent", "start");
                Intent intent_component_details=new Intent(context, ComponentDetailsActivity.class);
                Log.d("creating intent ", "end successfull");
                intent_component_details.putExtra("EXTRA_COMPONENT_ID",componentList.get(position).getUid());
                intent_component_details.putExtra("EXTRA USERNAME",((ShowComponentsActivity)context).getAttachedUserName());
                context.startActivity(intent_component_details);
            }
        });

    }

    @Override
    public int getItemCount() {
        return componentList.size();
    }


    public void setComponentList(List<Component> componentList){
        this.componentList=componentList;
        notifyDataSetChanged();
    }



    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private RelativeLayout parent;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtName=itemView.findViewById(R.id.recview_component_name);
            parent=itemView.findViewById(R.id.parent);
        }
}

}
