package com.werner.coffeecompass.repositories;

import com.werner.coffeecompass.models.Places;
import com.werner.coffeecompass.requests.CafesApiClient;

import java.util.List;

import androidx.lifecycle.LiveData;

public class CafeRepository {

    private static CafeRepository instance;
    private CafesApiClient mCafeApiClient;
    private String mLocation;

    public static CafeRepository getInstance() {

        if (instance == null) {
            instance = new CafeRepository();
        }
        return instance;
    }

    private CafeRepository() {

        mCafeApiClient = CafesApiClient.getInstance();
        mLocation = "";
    }

    public LiveData<List<Places>> getCafes() {

        return mCafeApiClient.getCafes();
    }

    public void updateCafeLocation() {

        String fairway = "39.036700,-94.624039";
        String downtown = "39.090314,-94.581999";
        mLocation = downtown;
        mCafeApiClient.clearNextPage();
        mCafeApiClient.updateCafes(downtown);
    }

    public void getMoreCafes() {

        if (!prevSearchCheck()) {
            updateCafeLocation();
        }

        mCafeApiClient.updateCafes(mLocation);
    }

    private boolean prevSearchCheck() {

        return mLocation != null || !mLocation.isEmpty() || !mCafeApiClient.getNextPage().isEmpty();
    }
}
