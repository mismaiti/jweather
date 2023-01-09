package com.jweatherinfo.di

import android.content.Context
import androidx.room.Room
import com.jweatherinfo.data.local.dao.FavoriteCityDao
import com.jweatherinfo.data.local.factory.JWeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): JWeatherDatabase {
        return Room.databaseBuilder(context, JWeatherDatabase::class.java, "jweather_db")
            .allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideFavoriteCityDao(jWeatherDatabase: JWeatherDatabase): FavoriteCityDao {
        return jWeatherDatabase.favoriteCityDao()
    }
}