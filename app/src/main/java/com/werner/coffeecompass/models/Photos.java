package com.werner.coffeecompass.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Photos implements Parcelable {

    private String photo_reference;

    public Photos(String photo_reference) {
        this.photo_reference = photo_reference;
    }

    public Photos() {
    }

    protected Photos(Parcel in) {
        photo_reference = in.readString();
    }

    public static final Creator<Photos> CREATOR = new Creator<Photos>() {
        @Override
        public Photos createFromParcel(Parcel in) {
            return new Photos(in);
        }

        @Override
        public Photos[] newArray(int size) {
            return new Photos[size];
        }
    };

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }

    @Override
    public String toString() {
        return "Photos{" +
                "photo_reference='" + photo_reference + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(photo_reference);
    }
}
