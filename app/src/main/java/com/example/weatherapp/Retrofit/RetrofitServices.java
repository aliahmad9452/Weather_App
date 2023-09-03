package com.example.weatherapp.Retrofit;

import com.example.weatherapp.currentweather.MDCurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitServices {

    @GET("v1/current.json")
    Call<MDCurrentWeather> getCurrentWeather(@Query("key") String appKey,
                                             @Query("q") String city,
                                             @Query("aqi") String aqi);
}
