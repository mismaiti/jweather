package com.jweatherinfo.data.event

import com.happyfresh.happyarch.Event
import com.jweatherinfo.data.models.WeatherInfo

class Loading: Event

open class Loaded<T>(val data: T?) : Event

class WeatherListLoaded(list: List<WeatherInfo>): Loaded<List<WeatherInfo>>(list)
