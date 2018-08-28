package com.example.amir.rehave.model;

public class ReplayDataModel {
    String replay;
    String name;
    String date;
    String time;
    String commentId;
    public ReplayDataModel() {
    }

    public ReplayDataModel(String name, String date, String time, String commentId,String replay) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.commentId = commentId;
        this.replay = replay;
    }



    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getReplay() {
        return replay;
    }
}
