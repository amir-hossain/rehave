package com.example.amir.rehave.model;

public class SignUpModel {
    private String name;
    private String center;
    private String password;
    private String id;

    public SignUpModel() {
    }

    public SignUpModel(String id,String name, String center, String password) {
        this.name = name;
        this.center = center;
        this.password = password;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCenter() {
        return center;
    }

    public String getPassword() {
        return password;
    }
}
