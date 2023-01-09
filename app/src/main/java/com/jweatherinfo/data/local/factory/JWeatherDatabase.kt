package com.jweatherinfo.data.local.factory

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jweatherinfo.data.local.dao.FavoriteCity
import com.jweatherinfo.data.local.dao.FavoriteCityDao

@Database(entities = [FavoriteCity::class], version = 1, exportSchema = false)
abstract class JWeatherDatabase: RoomDatabase() {
    abstract fun favoriteCityDao(): FavoriteCityDao
}