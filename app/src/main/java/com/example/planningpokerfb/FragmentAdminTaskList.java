package com.example.planningpokerfb;

import android.os.Bundle;
import android.util.Log;
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

public class FragmentAdminTaskList extends Fragment {
    RecyclerView recyclerView;
    Button btn_add_new_task;
    FirebaseDatabaseHelper myDb;
    String gId, gName;
    private static  final String TAG = FragmentAdminTaskList.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_task_list, container, false);

        Bundle bundle = getArguments();
        if(bundle !=null){
            gId = getArguments().getString("groupId");
            gName = getArguments().getString("groupName");
        }

        btn_add_new_task = view.findViewById(R.id.btn_add_new_task);
        myDb = new FirebaseDatabaseHelper();

        recyclerView = view.findViewById(R.id.rv_task_admin);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        btn_add_new_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentAddNewTask();
                Bundle args = new Bundle();
                args.putString("groupId", gId);
                args.putString("groupName", gName);
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
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Tasks> tNames = new ArrayList<>();
                for (DataSnapshot productSnapshot: dataSnapshot.getChildren()) {
                    Tasks task = productSnapshot.getValue(Tasks.class);
                    if (task.getGroupId().equals(gId)){
                        tNames.add(task);
                    }
                }
                Log.d(TAG, "adding task to list: "+ tNames.size());
                RecyclerViewTaskAdapter mAdapter = new RecyclerViewTaskAdapter(getActivity(), tNames);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
