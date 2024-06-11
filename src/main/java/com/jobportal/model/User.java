package com.jobportal.model;

public class User {
    private String firstName;
    private String lastName ;
    private String password;
    private String email;

    public User(String firstName , String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName  =lastName ;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setUsername(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
