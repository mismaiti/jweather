package com.jweatherinfo.data.event

import com.happyfresh.happyarch.Event

class OnSubmit<T>(val item: T): Event

class OnClick<T>(val item: T): Event