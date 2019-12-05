package com.example.planningpokerfb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerfb.Adapters.RecyclerViewGroupAdapterUser;
import com.example.planningpokerfb.DatabaseHelper.FirebaseDatabaseHelper;
import com.example.planningpokerfb.Models.Groups;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentUser extends Fragment {

    FirebaseDatabaseHelper myDb;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_user, container, false);
        myDb = new FirebaseDatabaseHelper();
        recyclerView = view.findViewById(R.id.rv_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        initGroups();
        return view;
    }
    private void initGroups(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Groups");

        ref.addValueEventListener(new ValueEventListener() {
            ArrayList<Groups> gNames = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot productSnapshot: dataSnapshot.getChildren()) {
                    Groups product = productSnapshot.getValue(Groups.class);
                    gNames.add(product);
                }
                RecyclerViewGroupAdapterUser mAdapter = new RecyclerViewGroupAdapterUser(getActivity(), gNames);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
