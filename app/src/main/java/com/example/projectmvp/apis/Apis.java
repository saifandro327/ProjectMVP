package com.example.projectmvp.apis;

import com.example.projectmvp.responses.FeedResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Apis {
    @GET("v0/app4YoMRjJR2uATii/Sheet1?api_key=keyg2M3xOiZZCa6lz")
    Call<List<FeedResponse>> getfeedData();
//    (@Query("txtFirstName") String firstName,
//                                 @Query("txtMiddleName") String middleName,
//                                 @Query("txtLastName") String lastName,
//                                 @Query("txtMobile") String mobile);

}
