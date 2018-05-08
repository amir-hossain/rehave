package com.example.amir.rehave.others;

public class CommentDataModel {
    String comment;
    String postId;
    String name;

    public CommentDataModel() {
    }

    public CommentDataModel(String comment, String postId,String name) {
        this.comment = comment;
        this.postId = postId;
        this.name = name;
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
}
