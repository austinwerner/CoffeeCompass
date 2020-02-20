package com.werner.coffeecompass.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Places implements Parcelable {

    private String name;
    private OpeningHours opening_hours;
    private Photos photos;
    private float rating;
    private int price_level;
    private Location location;
    private String vicinity;

    public Places(String name, OpeningHours opening_hours, Photos photos, float rating, int price_level, Location location, String vicinity) {
        this.name = name;
        this.opening_hours = opening_hours;
        this.photos = photos;
        this.rating = rating;
        this.price_level = price_level;
        this.location = location;
        this.vicinity = vicinity;
    }

    public Places() {
    }

    protected Places(Parcel in) {
        name = in.readString();
        opening_hours = in.readParcelable(OpeningHours.class.getClassLoader());
        photos = in.readParcelable(Photos.class.getClassLoader());
        rating = in.readFloat();
        price_level = in.readInt();
        location = in.readParcelable(Location.class.getClassLoader());
        vicinity = in.readString();
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

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    @Override
    public String toString() {
        return "Places{" +
                "name='" + name + '\'' +
                ", opening_hours=" + opening_hours +
                ", photos=" + photos +
                ", rating=" + rating +
                ", price_level=" + price_level +
                ", location=" + location +
                ", vicinity='" + vicinity + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeParcelable(opening_hours, i);
        parcel.writeParcelable(photos, i);
        parcel.writeFloat(rating);
        parcel.writeInt(price_level);
        parcel.writeParcelable(location, i);
        parcel.writeString(vicinity);
    }
}
