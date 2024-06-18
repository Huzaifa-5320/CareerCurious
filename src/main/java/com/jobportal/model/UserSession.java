package com.jobportal.model;

public class UserSession {
    private static UserSession instance;

    private String email;

    private UserSession() {
        // private constructor to prevent instantiation
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
