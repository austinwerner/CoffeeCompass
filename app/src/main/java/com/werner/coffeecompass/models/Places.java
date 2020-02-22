package com.werner.coffeecompass.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Places implements Parcelable {

    private String name;
    private OpeningHours opening_hours;
    private Photos[] photos;
    private float rating;
    private int price_level;
    private Geometry geometry;
    private String vicinity;
    private String place_id;

    public Places() {
    }

    public Places(String name, OpeningHours opening_hours, Photos[] photos, float rating, int price_level, Geometry geometry, String vicinity, String place_id) {
        this.name = name;
        this.opening_hours = opening_hours;
        this.photos = photos;
        this.rating = rating;
        this.price_level = price_level;
        this.geometry = geometry;
        this.vicinity = vicinity;
        this.place_id = place_id;
    }

    protected Places(Parcel in) {
        name = in.readString();
        opening_hours = in.readParcelable(OpeningHours.class.getClassLoader());
        photos = in.createTypedArray(Photos.CREATOR);
        rating = in.readFloat();
        price_level = in.readInt();
        geometry = in.readParcelable(Geometry.class.getClassLoader());
        vicinity = in.readString();
        place_id = in.readString();
    }

    public static final Creator<Places> CREATOR = new Creator<Places>() {
        @Override
        public Places createFromParcel(Parcel in) {
            return new Places(in);
        }

        @Override
        public Places[] newArray(int size) {
            return new Places[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(OpeningHours opening_hours) {
        this.opening_hours = opening_hours;
    }

    public Photos[] getPhotos() {
        return photos;
    }

    public void setPhotos(Photos[] photos) {
        this.photos = photos;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPrice_level() {
        return price_level;
    }

    public void setPrice_level(int price_level) {
        this.price_level = price_level;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeParcelable(opening_hours, i);
        parcel.writeTypedArray(photos, i);
        parcel.writeFloat(rating);
        parcel.writeInt(price_level);
        parcel.writeParcelable(geometry, i);
        parcel.writeString(vicinity);
        parcel.writeString(place_id);
    }

    @Override
    public String toString() {
        return "Places{" +
                "name='" + name + '\'' +
                ", opening_hours=" + opening_hours +
                ", photos=" + Arrays.toString(photos) +
                ", rating=" + rating +
                ", price_level=" + price_level +
                ", geometry=" + geometry +
                ", vicinity='" + vicinity + '\'' +
                ", place_id='" + place_id + '\'' +
                '}';
    }
}
