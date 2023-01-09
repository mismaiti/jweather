package com.jweatherinfo.ui.cityworld

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happyfresh.happyarch.EventObservable
import com.jweatherinfo.data.event.Loaded
import com.jweatherinfo.data.event.WeatherListLoaded
import com.jweatherinfo.data.local.dao.toFavoriteCity
import com.jweatherinfo.data.models.WeatherInfo
import com.jweatherinfo.data.repo.WeatherRepoImpl
import com.jweatherinfo.ui.component.favorite.FavoriteCityComponent
import com.jweatherinfo.ui.component.weather.WeatherDetailsComponent
import com.jweatherinfo.ui.component.weatherdaily.WeatherListComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CitiesViewModel @Inject constructor(
    private val weatherRepo: WeatherRepoImpl
) : ViewModel() {


    fun fetchCityWeather(eventObservable: EventObservable, query: String) {
        viewModelScope.launch {
            weatherRepo.getCityWeatherByQuery(query).collectLatest {
                val result = it.getOrThrow()
                eventObservable.emit(WeatherDetailsComponent::class.java, Loaded(result))
                fetchWeatherForecast(eventObservable, result.lat, result.lng)
            }
        }
    }

    fun fetchWeatherForecast(eventObservable: EventObservable, lat: Double, lng: Double) {
        viewModelScope.launch {
            weatherRepo.getWeatherForecast(lat, lng).collectLatest {
                eventObservable.emit(WeatherListComponent::class.java, WeatherListLoaded(it.getOrThrow()))
            }
        }
    }

    fun loadFavoriteCities(eventObservable: EventObservable) {
        viewModelScope.launch {
            weatherRepo.getAllFavoriteCities().collectLatest {
                eventObservable.emit(FavoriteCityComponent::class.java, Loaded(it.getOrThrow()))
            }
        }
    }

    fun saveAsFavoriteCity(eventObservable: EventObservable, weatherInfo: WeatherInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepo.saveFavoriteCity(weatherInfo.toFavoriteCity())
            delay(300)
            loadFavoriteCities(eventObservable)
        }
    }

}