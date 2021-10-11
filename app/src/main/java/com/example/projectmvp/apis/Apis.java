package com.example.projectmvp.apis;

import com.example.projectmvp.responses.FeedResponse;
import com.example.projectmvp.responses.GoogsheetResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Apis {
    @GET("v0/app4YoMRjJR2uATii/Sheet1?api_key=keyg2M3xOiZZCa6lz")
    Call<FeedResponse> getfeedData();



//    @GET("projectmvp/google_sheets/gbLTsoDbpQRyFZvU?tabId=Sheet1")
//    Call<GoogsheetResponse> getfeedData();
}
