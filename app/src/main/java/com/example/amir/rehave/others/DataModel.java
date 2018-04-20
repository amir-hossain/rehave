package com.example.amir.rehave.others;

public class DataModel {

    String title;
    String post;
    String id;
    int id_;

    public DataModel(String id,String title, String post) {
        this.title = title;
        this.post = post;
        this.id = id;
    }

    public DataModel(String name, int id_) {
        this.title = name;
        this.id_ = id_;

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
