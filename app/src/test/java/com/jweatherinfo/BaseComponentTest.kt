package com.jweatherinfo

import android.os.Looper
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before

open class BaseComponentTest<T> {

    @RelaxedMockK
    lateinit var lifecycleOwner: LifecycleOwner

    lateinit var lifecycle: Lifecycle

    var component: T? = null

    @RelaxedMockK
    lateinit var view: View

    @Before
    open fun setup() {
        MockKAnnotations.init(this)
        lifecycle = LifecycleRegistry(lifecycleOwner)

        every { lifecycleOwner.lifecycle } returns lifecycle

        mockkStatic(Looper::class)
        val looper = mockk<Looper> {
            every { thread } returns Thread.currentThread()
        }

        every { Looper.getMainLooper() } returns looper
    }

    @After
    fun after() {
        unmockkAll()
    }
}