package com.werner.coffeecompass.repositories;

import com.werner.coffeecompass.models.Places;
import com.werner.coffeecompass.requests.CafesApiClient;

import java.util.List;

import androidx.lifecycle.LiveData;

public class CafeRepository {

    private static CafeRepository instance;
    private CafesApiClient mCafeApiClient;

    public static CafeRepository getInstance() {

        if (instance == null) {
            instance = new CafeRepository();
        }
        return instance;
    }

    private CafeRepository() {

        mCafeApiClient = CafesApiClient.getInstance();
    }

    public LiveData<List<Places>> getCafes() {

        return mCafeApiClient.getCafes();
    }

    public void updateCafes() {

        mCafeApiClient.updateCafes();
    }
}
