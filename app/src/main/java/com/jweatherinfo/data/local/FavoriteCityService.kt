package com.jweatherinfo.data.local

import com.jweatherinfo.data.local.dao.FavoriteCity
import kotlinx.coroutines.flow.Flow

interface FavoriteCityService {
    suspend fun getAllCity(): Flow<Result<List<FavoriteCity>>>
    suspend fun deleteAllCity(ids: List<Long>)
    suspend fun insertCity(city: FavoriteCity)
}