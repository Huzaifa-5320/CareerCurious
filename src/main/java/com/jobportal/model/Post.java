package com.jobportal.model;

import java.util.ArrayList;

public class Post {
    private String title;
    private String companyName;
    private String location;
    private String pay;
    private String email;
    private String description;
    private int postId  ;

    public Post(String title, String companyName, String location, String pay, String email, String description) {
        this.title = title;
        this.companyName = companyName;
        this.location = location;
        this.pay = pay;
        this.email = email;
        this.description = description;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLocation() {
        return location;
    }

    public String getPay() {
        return pay;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getId(){
        return postId ;
    }
    public void setId(int postId){
        this.postId = postId ;
    }
}
