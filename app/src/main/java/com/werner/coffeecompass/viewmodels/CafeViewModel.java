package com.werner.coffeecompass.viewmodels;

import android.location.Location;
import android.util.Log;

import com.werner.coffeecompass.models.Places;
import com.werner.coffeecompass.repositories.CafeRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CafeViewModel extends ViewModel {

    private static final String TAG = "CafeViewModel";

    private CafeRepository mRepository;

    public CafeViewModel() {
        mRepository = CafeRepository.getInstance();
    }

    public LiveData<List<Places>> getCafes() {

        return mRepository.getCafes();
    }

    public LiveData<Boolean> isLoading() {

        return mRepository.isLoading();
    }

    public void updateCafeLocation(Location aLocation) {

        String locationString = aLocation.getLatitude() + "," + aLocation.getLongitude();
        mRepository.updateCafeLocation(locationString);
    }

    public void getMoreCafes() {

        if( mRepository.getMoreCafes()) {
            Log.d(TAG, "Getting more data");
        } else {
            Log.d(TAG, "no more data available");
        };
    }

    public String getCafePlaceId(int aPosition) {
        return mRepository.getCafes().getValue().get(aPosition).getPlace_id();
    }
}
