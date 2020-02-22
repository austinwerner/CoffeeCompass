package com.werner.coffeecompass.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.werner.coffeecompass.models.Places;

import java.util.List;

public class CafesResponse {

    @SerializedName("results")
    @Expose
    private List<Places> mCafes;

    public List<Places> getCafes() {
        return mCafes;
    }

    /*
    @SerializedName("next_page_token")
    @Expose
    private String mNextPage;

    public String getNextPage() {
        return mNextPage;
    }

    @Override
    public String toString() {
        return "CafesResponse{" +
                "mCafes=" + mCafes +
                ", mNextPage='" + mNextPage + '\'' +
                '}';
    }
     */
}
