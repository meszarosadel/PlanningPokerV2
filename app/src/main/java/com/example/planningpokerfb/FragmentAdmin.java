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

import com.example.planningpokerfb.DatabaseHelper.FirebaseDatabaseHelper;
import com.example.planningpokerfb.Models.Groups;
import com.example.planningpokerfb.Models.RecyclerViewGroupAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentAdmin extends Fragment {


    FirebaseDatabaseHelper myDb;
    //vars
    private ArrayList<Groups> gNames = new ArrayList<>();

    Button btn_add_new_group;

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_admin, container, false);

        btn_add_new_group = view.findViewById(R.id.btn_add_new_group);
        myDb = new FirebaseDatabaseHelper();

        recyclerView = view.findViewById(R.id.rv_admin);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        btn_add_new_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentAddGroup();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_id, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        initGroups();
        return view;
    }

    private void initGroups(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Groups");

// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot productSnapshot: dataSnapshot.getChildren()) {
                    Groups product = productSnapshot.getValue(Groups.class);
                    gNames.add(product);
                }
                RecyclerViewGroupAdapter mAdapter = new RecyclerViewGroupAdapter(getActivity(), gNames);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }





}
