package com.example.bugtracker.view.Bug.ShowBugs;

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

import com.example.bugtracker.Bug;
import com.example.bugtracker.R;
import com.example.bugtracker.view.Bug.BugDetails.BugDetailsActivity;

import java.util.List;

public class BugsRecViewAdapter extends RecyclerView.Adapter<BugsRecViewAdapter.ViewHolder> {

    private Context context;
    private List<Bug> bugslist;

    //since the adapter is outside the activity we need to know the parent activity
    //we pass the context-paretn activity in the constructor to be able to generate intents etc
    public BugsRecViewAdapter(Context context){this.context=context;}


    @Override
    //creates instance of viewholder class  for every item in our recview
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bug_list_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull  BugsRecViewAdapter.ViewHolder holder, int position) {
        holder.txtName.setText(bugslist.get(position).getName());
        //click on parent so we click on padding too.
        //create click listener

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("creating intent", "start");
                Intent intent_bug_details=new Intent(context, BugDetailsActivity.class);
                Log.d("creating intent ", "end successfull");
                intent_bug_details.putExtra("EXTRA_BUG_ID",bugslist.get(position).getId());
                intent_bug_details.putExtra("EXTRA USERNAME",((ShowBugsActivity)context).getAttachedUsername());
                context.startActivity(intent_bug_details);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bugslist.size();
    }


    public void setBugslist(List<Bug> bl){
        this.bugslist=bl;
        notifyDataSetChanged();
    }



    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private RelativeLayout parent;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtName=itemView.findViewById(R.id.text_bug_name);
            parent=itemView.findViewById(R.id.parent);
        }
}

}
