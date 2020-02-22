package com.werner.coffeecompass.requests;

import com.werner.coffeecompass.requests.responses.DetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetailsApi {

    // Get cafe detail
    @GET("place/details/json?")
    Call<DetailsResponse> getDetailResponse(
            @Query("key") String key,
            @Query("place_id") String place_id
    );
}
