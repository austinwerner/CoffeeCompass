package com.werner.coffeecompass.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {

    private float lat;
    private float lng;

    public Location(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Location() {
    }

    protected Location(Parcel in) {
        lat = in.readFloat();
        lng = in.readFloat();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Location{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(lat);
        parcel.writeFloat(lng);
    }
}
