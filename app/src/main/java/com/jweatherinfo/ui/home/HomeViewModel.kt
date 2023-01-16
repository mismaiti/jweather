package com.jweatherinfo.ui.home

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.happyfresh.happyarch.EventObservable
import com.jweatherinfo.core.util.LocationUtil.locationFlow
import com.jweatherinfo.data.event.Loaded
import com.jweatherinfo.data.event.WeatherListLoaded
import com.jweatherinfo.data.repo.WeatherRepoImpl
import com.jweatherinfo.ui.component.weather.WeatherDetailsComponent
import com.jweatherinfo.ui.component.weatherdaily.WeatherListComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepo: WeatherRepoImpl,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : ViewModel() {

    lateinit var location: Location

    fun fetchWeather(
        eventObservable: EventObservable,
        lat: Double = 0.0,
        lng: Double = 0.0,
        isCurrentLocation: Boolean
    ) {
        viewModelScope.launch {
            if (isCurrentLocation) {
//                eventObservable.emit(WeatherDetailsComponent::class.java, Loading())
                fusedLocationProviderClient.locationFlow().flatMapConcat {
                    location = it.getOrThrow()
                    weatherRepo.getWeather(location.latitude, location.longitude)
                }.collectLatest {
                    eventObservable.emit(
                        WeatherDetailsComponent::class.java,
                        Loaded(it.getOrThrow())
                    )
                    fetchWeatherForecast(eventObservable, location.latitude, location.longitude)
                }
            } else {
                weatherRepo.getWeather(lat, lng).collectLatest {
                    val weather = it.getOrThrow()
                    eventObservable.emit(WeatherDetailsComponent::class.java, Loaded(weather))
                }
            }
        }
    }

    fun fetchWeatherForecast(eventObservable: EventObservable, lat: Double, lng: Double) {
        viewModelScope.launch {
            weatherRepo.getWeatherForecast(lat, lng).collectLatest {
                eventObservable.emit(
                    WeatherListComponent::class.java,
                    WeatherListLoaded(it.getOrThrow())
                )
            }
        }
    }
}