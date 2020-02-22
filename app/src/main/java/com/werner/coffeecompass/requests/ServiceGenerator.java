package com.werner.coffeecompass.requests;

import com.werner.coffeecompass.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static CafesApi cafesApi = retrofit.create(CafesApi.class);

    public static CafesApi getCafeApi(){
        return cafesApi;
    }

    private static DetailsApi detailsApi = retrofit.create(DetailsApi.class);

    public static DetailsApi getDetailsApi(){
        return detailsApi;
    }

}
