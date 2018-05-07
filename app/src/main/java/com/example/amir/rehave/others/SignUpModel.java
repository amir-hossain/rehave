package com.example.amir.rehave.others;

public class SignUpModel {
    private String name;
    private String password;
    private String phoneEmail;
    private String id;

    public SignUpModel() {
    }

    public SignUpModel(String id,String name, String password, String phoneEmail) {
        this.name = name;
        this.password = password;
        this.phoneEmail = phoneEmail;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneEmail() {
        return phoneEmail;
    }
}
