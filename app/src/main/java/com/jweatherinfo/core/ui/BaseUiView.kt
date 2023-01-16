package com.jweatherinfo.core.ui

import android.view.View
import androidx.viewbinding.ViewBinding
import com.happyfresh.happyarch.EventObservable
import com.happyfresh.happyarch.UiView

open class BaseUiView<T : ViewBinding>(
    view: View,
    eventObservable: EventObservable,
    preBinding: () -> T
) :
    UiView(view, eventObservable) {

    var binding: T? = preBinding.invoke()

    fun show() {
        view.visibility = View.VISIBLE
    }

    fun hide() {
        view.visibility = View.GONE
    }

    fun onDestroyView() {
        binding = null
    }

    fun bindDimens(toDp: Int): Int {
        return view.context.resources.getDimensionPixelSize(toDp)
    }
}