package com.example.planningpokerfb.Models;

public class UserRole {
    String id;
    String email;
    String role;

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
