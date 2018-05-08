package com.example.amir.rehave.others;

public class CommentDataModel {
    String comment;
    String postId;
    String name;
    String date;
    String time;
    String commentId;
    String replayCount="0";
    public CommentDataModel() {
    }

    public CommentDataModel(String comment, String postId, String name, String date, String time, String commentId,String replayCount) {
        this.comment = comment;
        this.postId = postId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.commentId = commentId;
        this.replayCount=replayCount;
    }

    public CommentDataModel(String comment, String postId, String name, String date, String time, String commentId) {
        this.comment = comment;
        this.postId = postId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public String getPostId() {
        return postId;
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

    public String getReplayCount() {
        return replayCount;
    }
}
