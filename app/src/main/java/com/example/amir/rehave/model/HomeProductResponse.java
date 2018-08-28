package com.example.amir.rehave.model;

public class HomeProductResponse {
    private String title;

    private String writer;

    private String section;


    public HomeProductResponse(String title, String writer, String section) {
        this.title = title;
        this.writer = writer;
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
