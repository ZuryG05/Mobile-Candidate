package com.example.examen_android.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Timezone implements Parcelable {
    private String offset;
    private String description;

    protected Timezone(Parcel in) {
        offset = in.readString();
        description = in.readString();
    }

    public static final Creator<Timezone> CREATOR = new Creator<Timezone>() {
        @Override
        public Timezone createFromParcel(Parcel in) {
            return new Timezone(in);
        }

        @Override
        public Timezone[] newArray(int size) {
            return new Timezone[size];
        }
    };

    public String getOffset(){
        return offset;

    }
    public String getDescription(){
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(offset);
        dest.writeString(description);
    }
}
