package com.werner.coffeecompass.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.werner.coffeecompass.models.Detail;
import com.werner.coffeecompass.models.Photos;

import java.util.List;

public class DetailsResponse {

    @SerializedName("result")
    @Expose
    private Detail mDetail;

    public Detail getDetails() {
        return mDetail;
    }
}
