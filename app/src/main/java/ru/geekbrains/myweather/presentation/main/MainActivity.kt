package ru.geekbrains.myweather.presentation.main

import android.os.Bundle
import com.google.android.material.navigation.NavigationBarView
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweather.R
import ru.geekbrains.myweather.databinding.ActivityMainBinding
import ru.geekbrains.myweather.presentation.abs.AbsActivity
import ru.geekbrains.myweather.presentation.getMyLocation

class MainActivity : AbsActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter {
        MainPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter

        val toolbar = supportActionBar
        val navView = binding.navView as NavigationBarView
        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    presenter.clickHome()
                    toolbar?.title = it.title.toString()
                    true
                }
                R.id.search -> {
                    presenter.clickSearch()
                    toolbar?.title = it.title.toString()
                    true
                }
                R.id.settings -> {
                    presenter.clickSettings()
                    toolbar?.title = it.title.toString()
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun getLocation(): Pair<Double, Double> {
        return getMyLocation(1234)
    }
}