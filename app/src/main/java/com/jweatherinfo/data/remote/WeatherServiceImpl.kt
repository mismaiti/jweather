package com.jweatherinfo.data.remote

import com.jweatherinfo.core.network.BaseApiService
import com.jweatherinfo.data.remote.exception.HttpResponseException
import com.jweatherinfo.data.remote.response.CitiesResponse
import com.jweatherinfo.data.remote.response.WeatherForecastResponse
import com.jweatherinfo.data.remote.response.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class WeatherServiceImpl @Inject constructor(private val weatherService: WeatherService): BaseApiService() {

    companion object {
        const val METRIC_UNIT = "metric"
        const val API_KEY = "230103140c9289ae3dd4cbc697945104"
    }

    suspend fun getWeather(lat: Double, lng: Double): Flow<Result<WeatherResponse>> =
        observe {
            weatherService.getWeatherByLatLong(lat, lng, METRIC_UNIT, API_KEY)
        }

    suspend fun getWeatherForecast(
        lat: Double,
        lng: Double
    ): Flow<Result<WeatherForecastResponse>> =
        observe {
            weatherService.getDailyWeatherForecast(lat, lng, 24, METRIC_UNIT, API_KEY)
        }

    suspend fun getCityLatLongByQuery(query: String): Flow<Result<List<CitiesResponse>>> =
        observe {
            weatherService.getCityLatLongByName(query, API_KEY)
        }
}