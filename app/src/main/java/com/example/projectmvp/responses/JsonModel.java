package com.example.projectmvp.responses;

public class JsonModel {
    private String Name;
    private String Images;
    private String Caption;
    private String Profile_Picture;

    public JsonModel(String name, String images, String caption, String profile_Picture) {
        Name = name;
        Images = images;
        Caption = caption;
        Profile_Picture = profile_Picture;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public String getCaption() {
        return Caption;
    }

    public void setCaption(String caption) {
        Caption = caption;
    }

    public String getProfile_Picture() {
        return Profile_Picture;
    }

    public void setProfile_Picture(String profile_Picture) {
        Profile_Picture = profile_Picture;
    }
}
