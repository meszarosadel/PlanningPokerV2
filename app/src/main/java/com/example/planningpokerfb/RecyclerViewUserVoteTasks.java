package com.example.planningpokerfb;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerfb.Models.Tasks;

import java.util.ArrayList;


public class RecyclerViewUserVoteTasks extends RecyclerView.Adapter<RecyclerViewUserVoteTasks.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Tasks> mTask;
    private Context mContext;

    public RecyclerViewUserVoteTasks(Context context, ArrayList<Tasks> task ) {
        mTask = task;
        mContext = context;
    }

    public RecyclerViewUserVoteTasks.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_vote_item, parent, false);
        RecyclerViewUserVoteTasks.ViewHolder holder = new RecyclerViewUserVoteTasks.ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(RecyclerViewUserVoteTasks.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.tv_task_user.setText(mTask.get(position).getQuestion());


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

        TextView  tv_task_user;
        NumberPicker numberPicker;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.user_task_vote);
            tv_task_user = itemView.findViewById(R.id.tv_task_user);
            numberPicker = itemView.findViewById(R.id.numberPicker);
        }
    }

}
