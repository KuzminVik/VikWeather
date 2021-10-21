package ru.geekbrains.myweather.presentation.main

import android.content.SharedPreferences
import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.navigation.NavigationBarView
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweather.R
import ru.geekbrains.myweather.databinding.ActivityMainBinding
import ru.geekbrains.myweather.presentation.abs.AbsActivity
import ru.geekbrains.myweather.scheduler.ISchedulers
import javax.inject.Inject

class MainActivity : AbsActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val navigator = AppNavigator(this, R.id.container)

    @Inject
    lateinit var schedulers: ISchedulers

    @Inject
    lateinit var sharedPref: SharedPreferences

    private val presenter by moxyPresenter {
        MainPresenter(router = router, schedulers = schedulers) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = supportActionBar
        toolbar?.title = getString(R.string.my_weather)
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

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun getNameCity() {
        val name = sharedPref.getString("cityName", " ") ?: " "
        presenter.init(name)
    }

}