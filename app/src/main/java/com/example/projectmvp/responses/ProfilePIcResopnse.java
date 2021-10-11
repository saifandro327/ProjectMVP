package com.example.projectmvp.responses;

public class ProfilePIcResopnse {
    private ProfilePicture[] profilePictures;
    public ProfilePicture[] getProfilePictures(){
        return profilePictures;
    }
    public void setRecords(ProfilePicture[] records){
        this.profilePictures=records;
    }
}
