package com.example.amir.rehave.others;

public class DataModel {
    String id;
    String title;
    String post;


    public DataModel() {
    }

    public DataModel(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public DataModel(String id, String title, String post) {
        this.title = title;
        this.post = post;
        this.id = id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getTitle() {
        return title;
    }



    public String getId() {
        return id;
    }
}
