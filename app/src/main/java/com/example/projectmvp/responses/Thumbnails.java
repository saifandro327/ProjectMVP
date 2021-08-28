package com.example.projectmvp.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnails {

    @SerializedName("small")
    @Expose
    private Small small;
    @SerializedName("large")
    @Expose
    private Large large;
    @SerializedName("full")
    @Expose
    private Full full;

    public Small getSmall() {
        return small;
    }

    public void setSmall(Small small) {
        this.small = small;
    }

    public Large getLarge() {
        return large;
    }

    public void setLarge(Large large) {
        this.large = large;
    }

    public Full getFull() {
        return full;
    }

    public void setFull(Full full) {
        this.full = full;
    }

}
