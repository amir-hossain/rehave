package com.example.amir.rehave.others;

public class CommunityPostModel {
    private String userId;
    private String postId;
    private String post;
    private String name;
    private String date;
    private String time;
    private boolean reviewStatus;

    public CommunityPostModel() {
    }

    public CommunityPostModel(String userId, String postId, String post, String name, String date, String time,Boolean reviewStatus) {
        this.userId = userId;
        this.postId = postId;
        this.post = post;
        this.name = name;
        this.date = date;
        this.time = time;
        this.reviewStatus=reviewStatus;
    }


    public String getUserId() {
        return userId;
    }

    public String getPostId() {
        return postId;
    }

    public String getPost() {
        return post;
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

    public boolean getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(boolean reviewStatus) {
        this.reviewStatus = reviewStatus;
    }
}
