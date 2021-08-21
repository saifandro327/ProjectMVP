package com.example.projectmvp.model;

public class HomeFeedObjects {
    private int profileImage;
    private String Name;
    private int Post;
    private String Caption;

    public HomeFeedObjects(int profileImage, String name, int post, String caption) {
        this.profileImage = profileImage;
        Name = name;
        Post = post;
        Caption = caption;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPost() {
        return Post;
    }

    public void setPost(int post) {
        Post = post;
    }

    public String getCaption() {
        return Caption;
    }

    public void setCaption(String caption) {
        Caption = caption;
    }
}