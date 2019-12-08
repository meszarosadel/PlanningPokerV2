package com.example.planningpokerfb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerfb.Models.Tasks;
import com.example.planningpokerfb.Models.Votes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;


public class RecyclerViewUserVoteTasksAdapter extends RecyclerView.Adapter<RecyclerViewUserVoteTasksAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewUserVoteTasksAdapter";

    private ArrayList<Tasks> mVote;
    private Context mContext;
    String[] numbers = new String[] {"1", "3", "5", "8", "13", "21", "34", "55", "89"};

    public RecyclerViewUserVoteTasksAdapter(Context context, ArrayList<Tasks> vote ) {
        mVote = vote;
        mContext = context;
    }

    public RecyclerViewUserVoteTasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_vote_item, parent, false);
        RecyclerViewUserVoteTasksAdapter.ViewHolder holder = new RecyclerViewUserVoteTasksAdapter.ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(final RecyclerViewUserVoteTasksAdapter.ViewHolder holder, final int position) {

        holder.tv_task_user.setText(mVote.get(position).getQuestion());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext, mVote.get(position).getQuestion(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.btn_vote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Votes vote = new Votes(UUID.randomUUID().toString(), mVote.get(position).getQuestionId(),user.getUid(), numbers[holder.numberPicker.getValue()].toString());
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("Votes").child(vote.getVoteId()).setValue(vote);
                } else {
                    Toast.makeText(mContext, "Vote not submitted! Please try again!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    public int getItemCount() {
        return mVote.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout parentLayout;
        Button btn_vote;
        TextView tv_task_user;
        NumberPicker numberPicker;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.user_task_vote);
            tv_task_user = itemView.findViewById(R.id.tv_task_user);
            btn_vote = itemView.findViewById(R.id.btn_vote);

            numberPicker = itemView.findViewById(R.id.numberPicker);
            if (numberPicker != null) {
                String[] numbers = new String[] {"1", "3", "5", "8", "13", "21", "34", "55", "89"};
                numberPicker.setDisplayedValues(numbers);
                numberPicker.setMinValue(0);
                numberPicker.setMaxValue(numbers.length-1);
                numberPicker.setWrapSelectorWheel(true);
            }


        }
    }

}
