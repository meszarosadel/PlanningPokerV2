package com.example.planningpokerfb.Models;

import java.util.ArrayList;
import java.util.List;

public class Groups {
    private String groupId;
    private String groupName;
    private boolean active;
    private int timeSpan;
    private List<String> userIds;

   public String getGroupId(){
       return this.groupId;
   }

   public String getGroupName(){
       return this.groupName;
   }
   public boolean isActive(){
       return this.active;
   }
   public int getTimeSpan(){
       return this.timeSpan;
   }

   public Groups(String groupId, String groupName, boolean active, int timeSpan){
       this.groupId = groupId;
       this.groupName = groupName;
       this.active = active;
       this.timeSpan = timeSpan;
       this.userIds = new ArrayList<>();
   }

   public void addUser(String userId){
       this.userIds.add(userId);
   }
}
