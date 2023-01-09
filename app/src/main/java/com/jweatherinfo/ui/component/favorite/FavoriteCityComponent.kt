package com.jweatherinfo.ui.component.favorite

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.happyfresh.happyarch.EventObservable
import com.happyfresh.happyarch.Subscribe
import com.jweatherinfo.core.ui.BaseComponent
import com.jweatherinfo.data.event.Loaded
import com.jweatherinfo.data.local.dao.FavoriteCity

class FavoriteCityComponent(view: View, lifecycleOwner: LifecycleOwner) :
    BaseComponent<FavoriteCityUiView>(view, lifecycleOwner) {

    override fun onCreateView(view: View, eventObservable: EventObservable): FavoriteCityUiView {
        return FavoriteCityUiView(view, eventObservable)
    }

    @Subscribe(FavoriteCityComponent::class)
    fun subscribeFavoriteCities(loaded: Loaded<List<FavoriteCity>>) {
        loaded.data?.apply {
            uiView.show()
            uiView.loadData(this)
        }?: run {
            uiView.hide()
        }
    }
}