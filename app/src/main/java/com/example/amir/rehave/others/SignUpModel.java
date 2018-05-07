package com.example.amir.rehave.others;

public class SignUpModel {
    private String name;
    private String password;
    private String phoneEmail;

    public SignUpModel() {
    }

    public SignUpModel(String name, String password, String phoneEmail) {
        this.name = name;
        this.password = password;
        this.phoneEmail = phoneEmail;
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
