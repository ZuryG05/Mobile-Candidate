package com.example.examen_android.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Locat implements Parcelable {
    private Street street;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private Cordinates cordinates;
    private Timezone timezone;

    public Locat(Street street, String city, String state, String country, String postcode, Cordinates cordinates, Timezone timezone) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
        this.cordinates = cordinates;
        this.timezone = timezone;
    }

    protected Locat(Parcel in) {
        street = in.readParcelable(Street.class.getClassLoader());
        city = in.readString();
        state = in.readString();
        country = in.readString();
        postcode = in.readString();
        cordinates = in.readParcelable(Cordinates.class.getClassLoader());
        timezone = in.readParcelable(Timezone.class.getClassLoader());
    }

    public static final Creator<Locat> CREATOR = new Creator<Locat>() {
        @Override
        public Locat createFromParcel(Parcel in) {
            return new Locat(in);
        }

        @Override
        public Locat[] newArray(int size) {
            return new Locat[size];
        }
    };

    public Street getStreet() {

        return street;
    }

    public String getCity() {

        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry(){
        return country;

    }
    public String getPostcode(){
        return postcode;
    }

    public Cordinates getCordinates() {
        return cordinates;
    }
    public Timezone getTimezone(){
        return timezone;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(street, flags);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(postcode);
        dest.writeParcelable((Parcelable) cordinates, flags);
        dest.writeParcelable(timezone, flags);
    }
}