package com.jweatherinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jweatherinfo.android.R
import com.jweatherinfo.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_city_world
            )
        ).run {
            findNavController(R.id.nav_host_fragment_activity_main).let {
                setupActionBarWithNavController(it, this)
                binding.navView.setupWithNavController(it)
            }
        }

        supportActionBar?.hide()

    }
}