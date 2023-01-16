package com.jweatherinfo.data.remote.exception

import okhttp3.ResponseBody
import org.json.JSONObject

class HttpResponseException(val errorMessage: String): Throwable() {

    companion object {

        fun parseHttpException(errorBody: ResponseBody): HttpResponseException {
            val jsonObject = JSONObject(errorBody.toString())
            return HttpResponseException(jsonObject.getString("error"))
        }
    }
}