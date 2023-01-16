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

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): JWeatherDatabase {
        return Room.databaseBuilder(context, JWeatherDatabase::class.java, "jweather_db")
            .allowMainThreadQueries().build()
    }

    @Provides
    fun provideFavoriteCityDao(jWeatherDatabase: JWeatherDatabase): FavoriteCityDao {
        return jWeatherDatabase.favoriteCityDao()
    }
}