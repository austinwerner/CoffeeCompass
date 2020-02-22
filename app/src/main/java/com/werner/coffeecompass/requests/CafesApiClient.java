package com.werner.coffeecompass.requests;

import android.location.Location;
import android.util.Log;

import com.werner.coffeecompass.executors.AppExecutor;
import com.werner.coffeecompass.models.Places;
import com.werner.coffeecompass.requests.responses.CafesResponse;
import com.werner.coffeecompass.util.Constants;
import com.werner.coffeecompass.util.PrivateConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Response;

public class CafesApiClient {

    public static final String TAG = "CafesApiClient";

    private static CafesApiClient instance;
    private MutableLiveData<List<Places>> mCafes;
    private RetrieveCafesRunnable mRetrieveCafesRunnable;

    public static CafesApiClient getInstance() {

        if (instance == null) {
            instance = new CafesApiClient();
        }
        return instance;
    }

    private CafesApiClient() {

        mCafes = new MutableLiveData<>();
    }

    public LiveData<List<Places>> getCafes() {

        return mCafes;
    }

    public void updateCafes() {

        if (mRetrieveCafesRunnable != null) {
            mRetrieveCafesRunnable = null;
        }

        mRetrieveCafesRunnable = new RetrieveCafesRunnable();
        final Future handler = AppExecutor.get().networkIO().submit(mRetrieveCafesRunnable);

        // Set a timeout for the data refresh
        AppExecutor.get().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know it timed out
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveCafesRunnable implements Runnable {

        private RetrieveCafesRunnable() {
        }

        @Override
        public void run() {

            try {
                Response response = getCafes().execute();

                // Success code
                if (response.code() == 200) {
                    List<Places> list = new ArrayList<>(((CafesResponse)response.body()).getCafes());

                    /*
                    if (mPageToken.isEmpty()) {
                        mCafes.postValue(list);
                        Log.d(TAG, mCafes.toString() );
                        //mPageToken = ((CafesResponse)response.body()).getNextPage();
                    } else {
                        // After the first page, append to the list
                        List<Places> currentCafes = mCafes.getValue();
                        currentCafes.addAll(list);
                        mCafes.postValue(currentCafes);
                    }

                     */
                    mCafes.postValue(list);
                    Log.d(TAG, "Size = " + list.size());

                } else {
                    mCafes.postValue(null);
                    Log.d(TAG,"failed to get stuff");
                }
            } catch (Exception e) {
                e.printStackTrace();
                mCafes.postValue(null);
            }
        }

        private Call<CafesResponse> getCafes() {

            /*
            return ServiceGenerator.getCafeApi().getCafes(
                    PrivateConstants.API_KEY,
                    location.getLatitude() + "," + location.getLongitude(),
                    Constants.RANKBY,
                    Constants.TYPE,
                    pagetoken);

             */

            String fairway = "39.036700,-94.624039";
            String downtown = "39.090314,-94.581999";
            return ServiceGenerator.getCafeApi().getCafeResponse(
                    PrivateConstants.API_KEY,
                    fairway,
                    Constants.RANKBY,
                    Constants.TYPE,
                    "");
        }
    }
}
