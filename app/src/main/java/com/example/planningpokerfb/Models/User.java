package com.example.planningpokerfb.Models;

public class User {
    private String id;
    private String email;
    private enum role {USER,ADMIN};

    public User(){
        this.id="";
        this.email="";
    }
    public User(String id, String email){
        this.id=id;
        this.email=email;
    }

    public String getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public void setId(String id){
        this.id=id;
    }

    public void setEmail(String email){
        this.email=email;
    }

}
