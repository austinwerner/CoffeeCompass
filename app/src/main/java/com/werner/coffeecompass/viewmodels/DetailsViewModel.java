package com.werner.coffeecompass.viewmodels;

import com.werner.coffeecompass.models.Detail;
import com.werner.coffeecompass.repositories.DetailsRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DetailsViewModel extends ViewModel {

    private DetailsRepository mDetailsRepository;

    public DetailsViewModel() {

        mDetailsRepository = DetailsRepository.getInstance();
    }

    public LiveData<Detail> getDetail() {

        return mDetailsRepository.getDetail();
    }

    public void requestDetails(String placeId) {
        mDetailsRepository.requestDetails(placeId);
    }
}
