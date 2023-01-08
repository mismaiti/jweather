package com.jweatherinfo.ui.component.search

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.happyfresh.happyarch.EventObservable
import com.jweatherinfo.core.ui.BaseComponent

class SearchCitiesComponent(view: View, lifecycleOwner: LifecycleOwner) :
    BaseComponent<SearchCitiesUiView>(view, lifecycleOwner) {

    override fun onCreateView(view: View, eventObservable: EventObservable): SearchCitiesUiView {
        return SearchCitiesUiView(view, eventObservable)
    }

    override fun onCreate() {
        super.onCreate()
        uiView.setupSearchView()
    }
}