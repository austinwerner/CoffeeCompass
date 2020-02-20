package com.werner.coffeecompass.models;

import android.os.Parcel;
import android.os.Parcelable;

public class OpeningHours implements Parcelable {

    private boolean open_now;

    public OpeningHours(boolean open_now) {
        this.open_now = open_now;
    }

    public OpeningHours() {
    }

    protected OpeningHours(Parcel in) {
        open_now = in.readByte() != 0;
    }

    public static final Creator<OpeningHours> CREATOR = new Creator<OpeningHours>() {
        @Override
        public OpeningHours createFromParcel(Parcel in) {
            return new OpeningHours(in);
        }

        @Override
        public OpeningHours[] newArray(int size) {
            return new OpeningHours[size];
        }
    };

    public boolean isOpen_now() {
        return open_now;
    }

    public void setOpen_now(boolean open_now) {
        this.open_now = open_now;
    }

    @Override
    public String toString() {
        return "OpeningHours{" +
                "open_now=" + open_now +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (open_now ? 1 : 0));
    }
}
