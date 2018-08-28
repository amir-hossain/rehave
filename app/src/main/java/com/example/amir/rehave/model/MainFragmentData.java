package com.example.amir.rehave.model;

public class MainFragmentData {

    private String id;

    private String title;

    private String section;

//    need to fetch firebase data
    public MainFragmentData() {
    }

    public MainFragmentData(String id, String title, String section) {
        this.id = id;
        this.title = title;
        this.section = section;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
