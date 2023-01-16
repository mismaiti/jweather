package com.jweatherinfo.data.local

import com.jweatherinfo.data.local.dao.FavoriteCity
import com.jweatherinfo.data.local.dao.FavoriteCityDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteCityServiceImpl @Inject constructor(private val favoriteCityDao: FavoriteCityDao) :
    FavoriteCityService {
    override suspend fun getAllCity(): Flow<Result<List<FavoriteCity>>> = flow {
        val list = favoriteCityDao.getAllFavoriteCity()
        emit(Result.success(list))
    }

    override suspend fun deleteAllCity(ids: List<Long>) {
        favoriteCityDao.deleteCity(ids)
    }

    override suspend fun insertCity(city: FavoriteCity) {
        favoriteCityDao.insertCity(city)
    }
}