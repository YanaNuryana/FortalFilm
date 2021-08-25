package com.example.fortalfilm.retrofit;

import com.example.fortalfilm.model.ResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<ResponseMovie> ambilDataMovie(
        @Query("api_key") String apikey
    );
}



