package com.jweatherinfo.data.remote

import com.jweatherinfo.data.remote.response.CitiesResponse
import com.jweatherinfo.data.remote.response.WeatherForecastResponse
import com.jweatherinfo.data.remote.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("geo/1.0/direct")
    suspend fun getCityLatLongByName(
        @Query("q") query: String,
        @Query("appId") appId: String
    ): Response<List<CitiesResponse>>

    @GET("data/2.5/weather")
    suspend fun getWeatherByLatLong(
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
        @Query("units") units: String,
        @Query("appId") appId: String
    ): Response<WeatherResponse>

    @GET("data/2.5/forecast")
    suspend fun getDailyWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
        @Query("cnt") count: Int,
        @Query("units") units: String,
        @Query("appId") appId: String
    ): Response<WeatherForecastResponse>
}