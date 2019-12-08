package com.example.planningpokerfb.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerfb.Models.Tasks;
import com.example.planningpokerfb.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerViewTaskAdapter extends RecyclerView.Adapter<RecyclerViewTaskAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Tasks> mTask;
    private Context mContext;


    private DatabaseReference mDatabase;
    private DatabaseReference ref;

    public RecyclerViewTaskAdapter(Context context, ArrayList<Tasks> task ) {
        mTask = task;
        mContext = context;
    }


    public RecyclerViewTaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_new_task_item, parent, false);
        RecyclerViewTaskAdapter.ViewHolder holder = new RecyclerViewTaskAdapter.ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(RecyclerViewTaskAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.taskText.setText(mTask.get(position).getQuestion());
        holder.sw.setChecked(mTask.get(position).isActive());
        holder.sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                mTask.get(position).setActive();
                mDatabase = FirebaseDatabase.getInstance().getReference("Tasks");
                mDatabase.child(mTask.get(position).getQuestionId()).setValue(mTask.get(position));
                }
                if(!isChecked){
                    mTask.get(position).setInactive();
                    mDatabase = FirebaseDatabase.getInstance().getReference("Tasks");
                    mDatabase.child(mTask.get(position).getQuestionId()).setValue(mTask.get(position));
                }
            }
        });


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mTask.get(position));

                Toast.makeText(mContext, mTask.get(position).getQuestion(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int getItemCount() {
        return mTask.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout parentLayout;

        TextView  taskText;
        Switch sw;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.new_task_item);
            taskText = itemView.findViewById(R.id.tv_task);
            sw = itemView.findViewById(R.id.switch1);
        }
    }
}
