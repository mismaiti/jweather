package com.jweatherinfo.ui.component.search

import com.jweatherinfo.BaseComponentTest
import com.jweatherinfo.android.databinding.ComponentSearchCitiesBinding
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Test

internal class SearchCitiesComponentTest : BaseComponentTest<SearchCitiesComponent>() {

    @RelaxedMockK
    private lateinit var binding: ComponentSearchCitiesBinding

    override fun setup() {
        super.setup()

        mockkStatic(ComponentSearchCitiesBinding::class)
        every { ComponentSearchCitiesBinding.bind(view) } returns binding

        component = SearchCitiesComponent(view, lifecycleOwner)

    }


    @Test
    fun onCreate() {
        component?.onCreate()

        verify {
            component?.apply {
                uiView.setupSearchView()
            }
        }
    }
}