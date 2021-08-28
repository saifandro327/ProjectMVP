package com.example.projectmvp.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnails__1 {
    @SerializedName("small")
    @Expose
    private Small__1 small;
    @SerializedName("large")
    @Expose
    private Large__1 large;
    @SerializedName("full")
    @Expose
    private Full__1 full;

    public Small__1 getSmall() {
        return small;
    }

    public void setSmall(Small__1 small) {
        this.small = small;
    }

    public Large__1 getLarge() {
        return large;
    }

    public void setLarge(Large__1 large) {
        this.large = large;
    }

    public Full__1 getFull() {
        return full;
    }

    public void setFull(Full__1 full) {
        this.full = full;
    }
}
