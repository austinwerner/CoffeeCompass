package com.werner.coffeecompass.requests;

import com.werner.coffeecompass.requests.responses.CafesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CafesApi {

    // Search for articles
    @GET("place/nearbysearch/json?")
    Call<CafesResponse> getCafeResponse(
            @Query("key") String key,
            @Query("location") String location,
            @Query("rankby") String rankby,
            @Query("type") String type,
            @Query("pagetoken") String pagetoken
    );
}

