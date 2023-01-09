package com.jweatherinfo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteCityDao {

    @Query("SELECT * FROM favorite_city")
    fun getAllFavoriteCity(): List<FavoriteCity>

    @Query("DELETE FROM favorite_city WHERE id in (:ids)")
    fun deleteCity(ids: List<Long>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: FavoriteCity)
}