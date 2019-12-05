package com.example.planningpokerfb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerfb.Adapters.RecyclerViewTaskAdapter;
import com.example.planningpokerfb.DatabaseHelper.FirebaseDatabaseHelper;
import com.example.planningpokerfb.Models.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentUserTasks extends Fragment {

    RecyclerView recyclerView;
    Button btn_submit;
    FirebaseDatabaseHelper myDb;
    String tId, tName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_vote_tasks, container, false);
        tId = getArguments().getString("groupId");
        tName = getArguments().getString("groupName");

        btn_submit = view.findViewById(R.id.btn_submit);
        myDb = new FirebaseDatabaseHelper();

        recyclerView = view.findViewById(R.id.rv_task_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentUser();
                Bundle args = new Bundle();
                args.putString("groupId", tId);
                fragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_id, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        initTasks();
        return view;
    }

    private void initTasks(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Tasks");

// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            ArrayList<Tasks> tNames = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot productSnapshot: dataSnapshot.getChildren()) {
                    Tasks task = productSnapshot.getValue(Tasks.class);
                    if (tId.equals(task.getGroupId())){
                        tNames.add(task);
                    }
                }
                RecyclerViewTaskAdapter mAdapter = new RecyclerViewTaskAdapter(getActivity(), tNames);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
