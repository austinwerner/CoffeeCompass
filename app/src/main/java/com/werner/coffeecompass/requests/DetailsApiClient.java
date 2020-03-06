package com.werner.coffeecompass.requests;

import com.werner.coffeecompass.executors.AppExecutor;
import com.werner.coffeecompass.models.Detail;
import com.werner.coffeecompass.requests.responses.DetailsResponse;
import com.werner.coffeecompass.util.Constants;
import com.werner.coffeecompass.util.PrivateConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Response;

import static com.werner.coffeecompass.util.Constants.*;

public class DetailsApiClient {

    public static final String TAG = "CafesApiClient";

    private static DetailsApiClient instance;
    private MutableLiveData<Detail> mDetails;
    private RetrieveDetailsRunnable mRetrieveDetailsRunnable;

    public static DetailsApiClient getInstance() {

        if (instance == null) {
            instance = new DetailsApiClient();
        }
        return instance;
    }

    private DetailsApiClient() {

        mDetails = new MutableLiveData<>();
    }

    public LiveData<Detail> getDetail() {

        return mDetails;
    }

    public void clearDetails() {

        mDetails.setValue(null);
    }

    public void requestDetails(String placeId) {

        if (mRetrieveDetailsRunnable != null) {
            mRetrieveDetailsRunnable = null;
        }

        mRetrieveDetailsRunnable = new RetrieveDetailsRunnable(placeId);
        final Future handler = AppExecutor.get().networkIO().submit(mRetrieveDetailsRunnable);

        // Set a timeout for the data refresh
        AppExecutor.get().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know it timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveDetailsRunnable implements Runnable {

        String mPlaceId;

        private RetrieveDetailsRunnable(String placeId) {

            this.mPlaceId = placeId;
        }

        @Override
        public void run() {

            try {
                Response response = requestDetails().execute();

                // Success code
                if (response.code() == 200) {
                    Detail detail = ((DetailsResponse)response.body()).getDetails();
                    mDetails.postValue(detail);
                } else {
                    mDetails.postValue(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                mDetails.postValue(null);
            }
        }

        private Call<DetailsResponse> requestDetails() {

            return ServiceGenerator.getDetailsApi().getDetailResponse(
                    PrivateConstants.API_KEY,
                    mPlaceId );
        }
    }
}
