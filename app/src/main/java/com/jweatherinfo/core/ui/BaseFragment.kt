package com.jweatherinfo.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.happyfresh.happyarch.ComponentProvider
import com.happyfresh.happyarch.ComponentProviders
import com.happyfresh.happyarch.EventObservable

abstract class BaseFragment<T: ViewBinding> : Fragment() {

    protected var binding: T? = null

    protected val eventObservable: EventObservable by lazy { EventObservable.get(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initViewBinding(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponentProvider()
        EventObservable.bindSubscriber(this, eventObservable)
        onFragmentInitiated()
    }

    abstract fun onFragmentInitiated()

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    abstract fun initViewBinding(inflater: LayoutInflater): T

    private fun initComponentProvider() {
        bindComponent(ComponentProviders.of(this))
    }

    abstract fun bindComponent(provider: ComponentProvider)


}
