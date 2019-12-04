package com.example.planningpokerfb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.planningpokerfb.Models.Groups;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class FragmentAddGroup extends Fragment {

    TextView tv_add_grop;
    EditText et_add_group;
    Button btn_add_group;
    private DatabaseReference  mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_group, container, false);

        tv_add_grop = view.findViewById(R.id.tv_add_grop);
        et_add_group = view.findViewById(R.id.et_add_group);
        btn_add_group = view.findViewById(R.id.btn_add_group);

        btn_add_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupName = et_add_group.getText().toString();
                if (groupName.isEmpty()) {
                    et_add_group.setError("Please enter a group name!");
                    et_add_group.requestFocus();
                } else if (!(groupName.isEmpty())) {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Groups group = new Groups(UUID.randomUUID().toString(),groupName,true,20);
                    mDatabase.child("Groups").child(group.getGroupId()).setValue(group);



                    //DatabaseReference databaseRole= FirebaseDatabase.getInstance().getReference("Groups");
                    //databaseRole.child(FirebaseAuth.getInstance().getUid()).setValue(group);
                }

            }
        });

        return view;
    }



}
