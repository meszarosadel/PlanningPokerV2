package com.example.planningpokerfb.Models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRole {
    public String id;
    public String email;
    public String role;

    public UserRole(){

    }

    public UserRole(String id, String email, String role){
        this.id = id;
        this.email=email;
        this.role=role;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
