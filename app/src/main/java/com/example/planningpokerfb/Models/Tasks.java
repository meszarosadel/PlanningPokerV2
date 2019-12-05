package com.example.planningpokerfb.Models;

public class Tasks {
    private String questionId;
    private String groupId;
    private String question;
    private String activeFrom;
    private String activeUntil;
    private boolean active;

    public String getQuestionId() {
        return questionId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getQuestion() {
        return question;
    }

    public String getActiveFrom() {
        return activeFrom;
    }

    public String getActiveUntil() {
        return activeUntil;
    }

    public boolean isActive() {
        return active;
    }

    public Tasks(String questionId, String groupId, String question, boolean active){
        this.questionId = questionId;
        this.groupId = groupId;
        this.question = question;
        this.active = active;
    }

    public Tasks(String questionId, String groupId, String question, boolean active, String activeFrom, String activeUntil){
        this.questionId = questionId;
        this.groupId = groupId;
        this.question = question;
        this.active = active;
        this.activeFrom = activeFrom;
        this.activeUntil = activeUntil;
    }
    public Tasks(){}

}
