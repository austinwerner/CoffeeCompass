package com.werner.coffeecompass.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Detail implements Parcelable {

    Photos[] photos;
    String website;
    String url;

    public Detail(Photos[] photos, String website, String url) {
        this.photos = photos;
        this.website = website;
        this.url = url;
    }

    public Detail() {
    }

    protected Detail(Parcel in) {
        photos = in.createTypedArray(Photos.CREATOR);
        website = in.readString();
        url = in.readString();
    }

    public static final Creator<Detail> CREATOR = new Creator<Detail>() {
        @Override
        public Detail createFromParcel(Parcel in) {
            return new Detail(in);
        }

        @Override
        public Detail[] newArray(int size) {
            return new Detail[size];
        }
    };

    public Photos[] getPhotos() {
        return photos;
    }

    public void setPhotos(Photos[] photos) {
        this.photos = photos;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(photos, i);
        parcel.writeString(website);
        parcel.writeString(url);
    }
}
