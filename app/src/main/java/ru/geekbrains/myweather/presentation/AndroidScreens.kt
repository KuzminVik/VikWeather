package ru.geekbrains.myweather.presentation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.myweather.presentation.currentweather.CurrentWeatherFragment
import ru.geekbrains.myweather.presentation.search.SearchFragment
import ru.geekbrains.myweather.presentation.settings.SettingsFragment

class AndroidScreens: IScreens {
    override fun current(name: String): Screen = FragmentScreen { CurrentWeatherFragment.newInstance(name) }

    override fun setting(): Screen = FragmentScreen { SettingsFragment.newInstance() }

    override fun search(): Screen = FragmentScreen { SearchFragment.newInstance() }
}