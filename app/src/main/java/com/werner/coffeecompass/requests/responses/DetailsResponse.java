package com.werner.coffeecompass.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.werner.coffeecompass.models.Detail;

import java.util.List;

public class DetailsResponse {

    @SerializedName("results")
    @Expose
    private Detail mDetail;

    public Detail getDetails() {
        return mDetail;
    }

}
