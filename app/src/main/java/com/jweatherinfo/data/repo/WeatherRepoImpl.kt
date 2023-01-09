package com.jweatherinfo.data.repo

import com.jweatherinfo.data.local.FavoriteCityServiceImpl
import com.jweatherinfo.data.local.dao.FavoriteCity
import com.jweatherinfo.data.models.City
import com.jweatherinfo.data.models.WeatherInfo
import com.jweatherinfo.data.models.toWeatherInfo
import com.jweatherinfo.data.models.toWeatherInfoList
import com.jweatherinfo.data.remote.WeatherServiceImpl
import com.jweatherinfo.data.remote.response.WeatherResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    private val weatherServiceImpl: WeatherServiceImpl,
    private val favoriteCityServiceImpl: FavoriteCityServiceImpl
    ): WeatherRepo {

    override suspend fun getWeather(lat: Double, lon: Double): Flow<Result<WeatherInfo>> = channelFlow {
        weatherServiceImpl.getWeather(lat, lon).collectLatest {
            val weatherResponse = it.getOrThrow()
            send(Result.success(weatherResponse.toWeatherInfo()))
        }
    }.catch { error -> emit(Result.failure(error)) }.flowOn(Dispatchers.IO)

    override suspend fun getWeatherForecast(lat: Double, lon: Double): Flow<Result<List<WeatherInfo>>> = channelFlow {
        weatherServiceImpl.getWeatherForecast(lat, lon).collectLatest {
            val forecast = it.getOrThrow()
            val listResponse = mutableListOf<WeatherResponse>()
            val calendar = Calendar.getInstance()
            var day = calendar.get(Calendar.DAY_OF_MONTH)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
            forecast.weatherList.map { resp ->
                withContext(Dispatchers.Default) {
                    async {
                        calendar.time = dateFormat.parse(resp.dateText)?: Date()
                        val responseDay = calendar.get(Calendar.DAY_OF_MONTH)
                        if (day != responseDay) {
                            listResponse.add(resp)
                            day = responseDay
                        }
                    }
                }
            }.awaitAll()
            forecast.weatherList = listResponse
            send(Result.success(forecast.toWeatherInfoList()))
        }
    }

    override suspend fun getCityWeatherByQuery(query: String): Flow<Result<WeatherInfo>> = channelFlow {
        weatherServiceImpl.getCityLatLongByQuery(query).collectLatest {
            val citiesResponse = it.getOrThrow()
            weatherServiceImpl.getWeather(citiesResponse.lat, citiesResponse.lng).collectLatest { weather ->
                val weatherResponse = weather.getOrThrow()
                weatherResponse.cityResponse = citiesResponse
                send(Result.success(weatherResponse.toWeatherInfo()))
            }
        }
    }.catch { error -> emit(Result.failure(error)) }.flowOn(Dispatchers.IO)

    override suspend fun getFavoritesCitiesWeather(cities: List<FavoriteCity>): Flow<Result<List<WeatherInfo>>> = flow {
        val list = cities.map { city ->
            withContext(Dispatchers.Default) {
                async {
                    weatherServiceImpl.getWeather(city.lat, city.lng).single().getOrThrow().toWeatherInfo()
                }
            }
        }.awaitAll()
        emit(Result.success(list))
    }.catch { error -> emit(Result.failure(error)) }

    override suspend fun getAllFavoriteCities(): Flow<Result<List<FavoriteCity>>> = channelFlow {
        favoriteCityServiceImpl.getAllCity().collectLatest {
            send(Result.success(it.getOrThrow()))
        }
    }.catch { error -> emit(Result.failure(error)) }

    override suspend fun saveFavoriteCity(city: FavoriteCity) {
        flowOf(favoriteCityServiceImpl.insertCity(city)).flowOn(Dispatchers.IO)
    }

    override suspend fun deleteCities(ids: List<Long>) {
        favoriteCityServiceImpl.deleteAllCity(ids)
    }
}