package com.jweatherinfo.data.repo

import com.jweatherinfo.data.models.City
import com.jweatherinfo.data.models.WeatherInfo
import com.jweatherinfo.data.remote.response.CitiesResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepo {
    suspend fun getWeather(lat: Double, lon: Double): Flow<Result<WeatherInfo>>
    suspend fun getWeatherForecast(lat: Double, lon: Double): Flow<Result<List<WeatherInfo>>>
    suspend fun getCityWeatherByQuery(query: String): Flow<Result<WeatherInfo>>
    suspend fun getFavoritesCitiesWeather(cities: List<City>): Flow<Result<List<WeatherInfo>>>
}