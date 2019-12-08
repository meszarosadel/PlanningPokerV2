package com.example.planningpokerfb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerfb.Models.Tasks;
import com.example.planningpokerfb.Models.Votes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentResultsAdapter extends RecyclerView.Adapter<FragmentResultsAdapter.ViewHolder> {

    private ArrayList<Tasks> mVote;
    private Context mContext;

    public FragmentResultsAdapter(Context context, ArrayList<Tasks> vote ) {
        mVote = vote;
        mContext = context;
    }

    public FragmentResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false);
        FragmentResultsAdapter.ViewHolder holder = new FragmentResultsAdapter.ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(final FragmentResultsAdapter.ViewHolder holder, final int position) {

        holder.tv_task_res.setText(mVote.get(position).getQuestion());
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Votes");

// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(DataSnapshot dataSnapshot) {
                                          ArrayList<Votes> votes = new ArrayList<>();
                                          for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                                              Votes vote = productSnapshot.getValue(Votes.class);
                                              if (vote.getTaskId().equals(mVote.get(position).getQuestionId())) {
                                                  votes.add(vote);
                                              }
                                          }
                                          float avg = 0f;
                                          if (votes.size() > 0){
                                            for (Votes vote: votes){
                                                avg += (Float.parseFloat(vote.getAnswer()));
                                            }
                                            avg = avg/ votes.size();
                                          }
                                          holder.tv_score_res.setText(Float.toString(avg));
                                      }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
                                  });


    }
    public int getItemCount() {
        return mVote.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout parentLayout;
        TextView tv_task_res, tv_score_res;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.results);
            tv_score_res = itemView.findViewById(R.id.tv_score_res);
            tv_task_res = itemView.findViewById(R.id.tv_task_res);
        }
    }

}
