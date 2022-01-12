package ru.geekbrains.myweather.presentation.settings

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import moxy.MvpPresenter
import ru.geekbrains.myweather.util.KEY_DAILY
import ru.geekbrains.myweather.util.KEY_HOURLY
import ru.geekbrains.myweather.util.KEY_THEME

class SettingsPresenter(
    val sharedPreferences: SharedPreferences
): MvpPresenter<SettingsView>() {

    override fun onFirstViewAttach() {
        viewState.initTheme(getSavedTheme())
        viewState.initHourlySettings(isEnabledHourlyForecast())
        viewState.initDailySettings(isEnabledDailyForecast())
    }

    fun setTheme(themeMode: Int, prefsMode: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        saveTheme(prefsMode)
    }

    fun enabledHourlyForecast(value: Boolean) =
        sharedPreferences.edit().putBoolean(KEY_HOURLY, value).apply()

    fun enabledDailyForecast(value: Boolean) =
        sharedPreferences.edit().putBoolean(KEY_DAILY, value).apply()

    private fun saveTheme(theme: Int) = sharedPreferences.edit().putInt(KEY_THEME, theme).apply()
    private fun getSavedTheme() = sharedPreferences.getInt(KEY_THEME, -1)

    private fun isEnabledHourlyForecast() = sharedPreferences.getBoolean(KEY_HOURLY, true)
    private fun isEnabledDailyForecast() = sharedPreferences.getBoolean(KEY_DAILY, true)

}