package com.jweatherinfo.data.remote

import com.jweatherinfo.data.remote.response.CitiesResponse
import com.jweatherinfo.data.remote.response.WeatherForecastResponse
import com.jweatherinfo.data.remote.response.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherServiceImpl @Inject constructor(private val weatherService: WeatherService) {

    companion object {
        const val METRIC_UNIT = "metric"
        const val API_KEY = "230103140c9289ae3dd4cbc697945104"
    }

    suspend fun getWeather(lat: Double, lng: Double): Flow<Result<WeatherResponse>> = flow {
        val response = weatherService.getWeatherByLatLong(lat, lng, METRIC_UNIT, API_KEY)
        response.body()?.let {
            emit(Result.success(it))
        } ?: run {
            Result.failure<Throwable>(Throwable("${response.code()}"))
        }
    }.catch { error ->
        Result.failure<Throwable>(error)
    }.flowOn(Dispatchers.IO)

    suspend fun getWeatherForecast(lat: Double, lng: Double): Flow<Result<WeatherForecastResponse>> =
        flow {
            val response =
                weatherService.getDailyWeatherForecast(lat, lng, 24, METRIC_UNIT, API_KEY)
            response.body()?.let {
                emit(Result.success(it))
            } ?: run {
                Result.failure<Throwable>(Throwable("${response.code()}"))
            }
        }.catch { error -> Result.failure<Throwable>(error) }.flowOn(Dispatchers.IO)

    suspend fun getCityLatLongByQuery(query: String): Flow<Result<CitiesResponse>> = flow {
        val response = weatherService.getCityLatLongByName(query, API_KEY)
        response.body()?.let {
            emit(Result.success(it[0]))
        } ?: run {
            Result.failure<Throwable>(Throwable("${response.code()}"))
        }
    }.catch { error ->
        Result.failure<Throwable>(error)
    }.flowOn(Dispatchers.IO)
}