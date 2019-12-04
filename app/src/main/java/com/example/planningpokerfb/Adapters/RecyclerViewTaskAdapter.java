package com.example.planningpokerfb.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerfb.Models.RecyclerViewGroupAdapter;
import com.example.planningpokerfb.Models.Tasks;
import com.example.planningpokerfb.R;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class RecyclerViewTaskAdapter {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Tasks> mTask;
    private Context mContext;

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

        holder.btn_add_task.setText(mTask.get(position).getQuestion());

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

        Button btn_add_task;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            btn_add_task = itemView.findViewById(R.id.btn_add_task);
            parentLayout = itemView.findViewById(R.id.admin_group_item);
        }
    }
}
