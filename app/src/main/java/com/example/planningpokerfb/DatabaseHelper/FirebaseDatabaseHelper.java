package com.example.planningpokerfb.DatabaseHelper;

import androidx.annotation.NonNull;

import com.example.planningpokerfb.Models.UserRole;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceUserRoles;
    private List<UserRole> userRoles = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<UserRole> userRoles,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public  FirebaseDatabaseHelper(){
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceUserRoles = mDatabase.getReference("UserRoles");
    }

    public void readUserRoles(){
        mReferenceUserRoles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    UserRole userRole = keyNode.getValue(UserRole.class);
                    userRoles.add(userRole);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
