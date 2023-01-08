package com.jweatherinfo.core.ext

import android.view.View

// region: view
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

// endregion