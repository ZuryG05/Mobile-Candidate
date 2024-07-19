package com.example.examen_android.modelo;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class resultados {
    @SerializedName("results")
    private ArrayList<User> results;

    public ArrayList<User> getResults() {
        return results;
        }
    }


