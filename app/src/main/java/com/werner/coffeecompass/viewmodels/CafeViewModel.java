package com.werner.coffeecompass.viewmodels;

import com.werner.coffeecompass.models.Places;
import com.werner.coffeecompass.repositories.CafeRepository;
import com.werner.coffeecompass.requests.CafesApiClient;
import com.werner.coffeecompass.requests.responses.CafesResponse;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CafeViewModel extends ViewModel {

    private CafeRepository mRepository;

    public CafeViewModel() {

        mRepository = CafeRepository.getInstance();
    }

    public LiveData<List<Places>> getCafes() {

        return mRepository.getCafes();
    }

    public void updateCafeLocation() {

        mRepository.updateCafeLocation();
    }

    public void getMoreCafes() {

        mRepository.getMoreCafes();
    }
}
