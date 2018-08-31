package com.example.amir.rehave.model;

public class CommunityPostModel {
    private String userId;
    private String postId;
    private String post;
    private String title;
    private String name;
    private String date;
    private String time;
    private String commentCount="0";
    private String image=null;
    private boolean reviewStatus;

    public CommunityPostModel() {
    }

    public CommunityPostModel(String userId, String postId,String title, String post, String name, String date, String time,Boolean reviewStatus) {
        this.userId = userId;
        this.postId = postId;
        this.title=title;
        this.post = post;
        this.name = name;
        this.date = date;
        this.time = time;
        this.reviewStatus=reviewStatus;
    }


    public CommunityPostModel(String userId, String postId, String title, String post, String name, String date, String time, Boolean reviewStatus,String image) {
        this.userId = userId;
        this.postId = postId;
        this.title=title;
        this.post = post;
        this.name = name;
        this.date = date;
        this.time = time;
        this.reviewStatus=reviewStatus;
        this.image=image;
    }
    public CommunityPostModel(String userId, String postId, String title, String post, String name, String date, String time, Boolean reviewStatus, String commentCount,String img) {
        this.userId = userId;
        this.postId = postId;
        this.title=title;
        this.post = post;
        this.name = name;
        this.date = date;
        this.time = time;
        this.reviewStatus=reviewStatus;
        this.commentCount=commentCount;
        this.image=img;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
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

    public String getTitle() {
        return title;
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


    public String getImage() {
        return image;
    }


}
