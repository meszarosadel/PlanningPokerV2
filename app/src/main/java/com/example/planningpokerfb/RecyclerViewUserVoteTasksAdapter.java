package com.example.planningpokerfb;

import android.content.Context;
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


public class RecyclerViewUserVoteTasksAdapter extends RecyclerView.Adapter<RecyclerViewUserVoteTasksAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewUserVoteTasksAdapter";

    private ArrayList<Tasks> mVote;
    private Context mContext;

    public RecyclerViewUserVoteTasksAdapter(Context context, ArrayList<Tasks> vote ) {
        mVote = vote;
        mContext = context;
    }

    public RecyclerViewUserVoteTasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_vote_item, parent, false);
        RecyclerViewUserVoteTasksAdapter.ViewHolder holder = new RecyclerViewUserVoteTasksAdapter.ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(RecyclerViewUserVoteTasksAdapter.ViewHolder holder, final int position) {

        holder.tv_task_user.setText(mVote.get(position).getQuestion());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext, mVote.get(position).getQuestion(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    public int getItemCount() {
        return mVote.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout parentLayout;

        TextView tv_task_user;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.user_task_vote);
            tv_task_user = itemView.findViewById(R.id.tv_task_user);

            NumberPicker numberPicker = itemView.findViewById(R.id.numberPicker);
            if (numberPicker != null) {
                String[] numbers = new String[] {"1", "3", "5", "8", "13", "21", "34", "55", "89"};
                numberPicker.setDisplayedValues(numbers);
                numberPicker.setMinValue(0);
                numberPicker.setMaxValue(numbers.length-1);
                numberPicker.setWrapSelectorWheel(true);
                numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        String text = "Changed from " + oldVal + " to " + newVal;
                        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
                    }
                });
            }


        }
    }

}
