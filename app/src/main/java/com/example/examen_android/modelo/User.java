package com.example.examen_android.modelo;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable {
    private Name name;
    private String email;
    private String gender;
    private String phone;
    private Locat location;
    private Picture picture;

    public User(Name name, String email, String gender, String phone, Locat location, Picture picture) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.location = location;
        this.picture = picture;
    }


    protected User(Parcel in) {
        name = in.readParcelable(Name.class.getClassLoader());
        email = in.readString();
        gender = in.readString();
        phone = in.readString();
        location = in.readParcelable(Locat.class.getClassLoader());
        picture = in.readParcelable(Picture.class.getClassLoader());
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(name, flags);
        dest.writeString(email);
        dest.writeString(gender);
        dest.writeString(phone);
        dest.writeParcelable(location, flags);
        dest.writeParcelable(picture, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public Name getName() {

        return name;
    }

    public String getEmail() {

        return email;
    }

    public String getGenero() {

        return gender;
    }

    public String getTelefono() {

        return phone;
    }

    public Locat getLocation() {
        return location;
    }

    public Picture getPicture() {

        return picture;
}

}