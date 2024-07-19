package com.example.examen_android.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Street implements Parcelable {
    private String number;
    private String name;

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    protected Street(Parcel in) {
        number = in.readString();
        name = in.readString();
    }

    public static final Creator<Street> CREATOR = new Creator<Street>() {
        @Override
        public Street createFromParcel(Parcel in) {
            return new Street(in);
        }

        @Override
        public Street[] newArray(int size) {
            return new Street[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(number);
        dest.writeString(name);
    }
}