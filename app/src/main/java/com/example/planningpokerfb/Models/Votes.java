package com.example.planningpokerfb.Models;

public class Votes {
    private String voteId;
    private String taskId;
    private String userId;
    private String answer;

    public Votes(String voteId, String taskId, String userId, String answer) {
        this.voteId = voteId;
        this.taskId = taskId;
        this.userId = userId;
        this.answer = answer;
    }

    public String getVoteId() {
        return voteId;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getUserId() {
        return userId;
    }

    public String getAnswer() {
        return answer;
    }
}
