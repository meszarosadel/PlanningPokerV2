package com.example.planningpokerfb.Models;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerfb.FragmentAdminTaskList;
import com.example.planningpokerfb.MainActivity;
import com.example.planningpokerfb.R;

import java.util.ArrayList;

public class RecyclerViewGroupAdapter extends RecyclerView.Adapter<RecyclerViewGroupAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Groups> mGroup;
    private Context mContext;

    public RecyclerViewGroupAdapter(Context context, ArrayList<Groups> Group ) {
        mGroup = Group;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_exising_group_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.btn_adm_group.setText(mGroup.get(position).getGroupName());
        holder.btn_adm_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FragmentAdminTaskList();
                Bundle args = new Bundle();
                args.putString("groupId", mGroup.get(position).getGroupId());
                args.putString("groupName", mGroup.get(position).getGroupName());
                fragment.setArguments(args);
                MainActivity activity = (MainActivity) view.getContext();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_id, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Toast.makeText(mContext, mGroup.get(position).getGroupName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGroup.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        Button btn_adm_group;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            btn_adm_group = itemView.findViewById(R.id.btn_adm_group);
            parentLayout = itemView.findViewById(R.id.admin_group_item);
        }
    }
}