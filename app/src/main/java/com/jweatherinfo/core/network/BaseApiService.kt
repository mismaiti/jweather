package com.jweatherinfo.core.network

import com.jweatherinfo.data.remote.exception.HttpResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

open class BaseApiService {

    suspend inline fun <T : Any> observe(crossinline apiCall: suspend () -> Response<T>): Flow<Result<T>> =
        flow<Result<T>> {
            val response = apiCall.invoke()
            response.body()?.let {
                emit(Result.success(it))
            } ?: run {
                if (response.code() > 400) {
                    response.errorBody()?.let {
                        emit(Result.failure(HttpResponseException.parseHttpException(it)))
                    }
                }
            }
        }.catch { error -> emit(Result.failure(error)) }.flowOn(Dispatchers.IO)
}