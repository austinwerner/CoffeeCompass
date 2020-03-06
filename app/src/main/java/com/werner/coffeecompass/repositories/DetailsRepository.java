package com.werner.coffeecompass.repositories;

import com.werner.coffeecompass.models.Detail;
import com.werner.coffeecompass.requests.DetailsApiClient;

import androidx.lifecycle.LiveData;

public class DetailsRepository {

    private static DetailsRepository instance;
    private DetailsApiClient mDetailsApiClient;

    public static DetailsRepository getInstance() {

        if (instance == null) {
            instance = new DetailsRepository();
        }
        return instance;
    }

    private DetailsRepository() {

        mDetailsApiClient = DetailsApiClient.getInstance();
    }

    public LiveData<Detail> getDetail() {
        return mDetailsApiClient.getDetail();
    }

    public void requestDetails(String placeId) {
        mDetailsApiClient.requestDetails(placeId);
    }

    public void clearDetails() {
        mDetailsApiClient.clearDetails();
    }
}
