package com.example.examen_android.interf;

import com.example.examen_android.modelo.resultados;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface randomuserAPI {
    //CREAMOS METODO PARA OBTENER INFO
    @GET("api/")
    Call<resultados> getUser(@Query("results") int results);

    @GET("api/")
    Call<resultados> getUsersByGender(@Query("results") int results,
                                      @Query("gender") String gender);

}
