package ru.geekbrains.myweather.presentation.currentweather

import androidx.fragment.app.Fragment
import ru.geekbrains.myweather.databinding.FragmentCurrentWeatherBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweather.presentation.abs.AbsFragment
import ru.geekbrains.myweather.R.layout.fragment_current_weather
import ru.geekbrains.myweather.presentation.arguments

class CurrentWeatherFragment : AbsFragment(fragment_current_weather), CurrentWeatherView {

    private val viewBinding: FragmentCurrentWeatherBinding by viewBinding()

    companion object {
        private const val TAG = "CurrentWeatherFragment"
        fun newInstance(): Fragment =
            CurrentWeatherFragment().arguments()
    }

    private val presenter: CurrentWeatherPresenter by moxyPresenter {
        CurrentWeatherPresenter() }


}