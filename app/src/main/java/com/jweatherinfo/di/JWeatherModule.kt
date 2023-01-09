package com.jweatherinfo.di

import com.google.android.gms.location.FusedLocationProviderClient
import com.jweatherinfo.data.local.FavoriteCityServiceImpl
import com.jweatherinfo.data.local.dao.FavoriteCityDao
import com.jweatherinfo.data.remote.WeatherService
import com.jweatherinfo.data.remote.WeatherServiceImpl
import com.jweatherinfo.data.repo.WeatherRepoImpl
import com.jweatherinfo.ui.cityworld.CitiesViewModel
import com.jweatherinfo.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JWeatherModule {

    @Singleton
    @Provides
    fun provideWeatherServiceImpl(weatherService: WeatherService): WeatherServiceImpl {
        return WeatherServiceImpl(weatherService)
    }

    @Singleton
    @Provides
    fun provideWeatherRepo(
        weatherServiceImpl: WeatherServiceImpl,
        favoriteCityServiceImpl: FavoriteCityServiceImpl
    ): WeatherRepoImpl {
        return WeatherRepoImpl(weatherServiceImpl, favoriteCityServiceImpl)
    }

    @Singleton
    @Provides
    fun provideFavoriteCityService(favoriteCityDao: FavoriteCityDao): FavoriteCityServiceImpl {
        return FavoriteCityServiceImpl(favoriteCityDao)
    }

    @Singleton
    @Provides
    fun provideHomeViewModel(
        weatherRepoImpl: WeatherRepoImpl,
        fusedLocationProviderClient: FusedLocationProviderClient
    ): HomeViewModel {
        return HomeViewModel(weatherRepoImpl, fusedLocationProviderClient)
    }

    @Singleton
    @Provides
    fun provideCitiesViewModel(weatherRepoImpl: WeatherRepoImpl): CitiesViewModel {
        return CitiesViewModel(weatherRepoImpl)
    }
}