package com.jweatherinfo.data.repo

import com.jweatherinfo.data.local.dao.FavoriteCity
import com.jweatherinfo.data.models.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherRepo {
    suspend fun getWeather(lat: Double, lon: Double): Flow<Result<WeatherInfo>>
    suspend fun getWeatherForecast(lat: Double, lon: Double): Flow<Result<List<WeatherInfo>>>
    suspend fun getCityWeatherByQuery(query: String): Flow<Result<WeatherInfo>>
    suspend fun getFavoritesCitiesWeather(cities: List<FavoriteCity>): Flow<Result<List<WeatherInfo>>>
    suspend fun getAllFavoriteCities(): Flow<Result<List<FavoriteCity>>>
    suspend fun saveFavoriteCity(city: FavoriteCity)
    suspend fun deleteCities(ids: List<Long>)
}