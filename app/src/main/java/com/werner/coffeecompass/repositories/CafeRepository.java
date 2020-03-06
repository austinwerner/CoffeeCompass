package com.werner.coffeecompass.repositories;

import android.location.Location;

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

    public LiveData<Boolean> isLoading() {

        return mCafeApiClient.isLoading();
    }

    public void updateCafeLocation(String aLocation) {

        mLocation = aLocation;
        mCafeApiClient.clearNextPage();
        mCafeApiClient.updateCafes(mLocation);
    }

    public boolean getMoreCafes() {

        // looking for more, but out of data
        if( !mCafeApiClient.isMoreData() ) {
            return false;
        }

        // we have a location, get more data
        if (prevSearchCheck()) {
            mCafeApiClient.updateCafes(mLocation);
        }

        return true;
    }

    private boolean prevSearchCheck() {

        return mLocation != null || !mLocation.isEmpty() || !mCafeApiClient.getNextPage().isEmpty();
    }
}
