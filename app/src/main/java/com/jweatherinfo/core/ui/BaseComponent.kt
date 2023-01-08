package com.jweatherinfo.core.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.happyfresh.happyarch.Component

abstract class BaseComponent<T : BaseUiView<out ViewBinding>>(
    view: View,
    lifecycleOwner: LifecycleOwner
) : Component<T>(view, lifecycleOwner) {

    override fun onDestroy() {
        super.onDestroy()
        uiView.onDestroyView()
    }

}