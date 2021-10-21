package ru.geekbrains.myweather.presentation.settings

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

class SettingsPresenter(
    val sharedPreferences: SharedPreferences
): MvpPresenter<SettingsView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        viewState.initThemeListener()
        viewState.initTheme(getSavedTheme())
        viewState.initHourlySettings(isEnabledHourlyForecast())
        viewState.initDailySettings(isEnabledDailyForecast())
    }

    fun setTheme(themeMode: Int, prefsMode: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        saveTheme(prefsMode)
    }

    fun enabledHourlyForecast(value: Boolean) =
        sharedPreferences.edit().putBoolean("KEY_HOURLY", value).apply()


    fun enabledDailyForecast(value: Boolean) =
        sharedPreferences.edit().putBoolean("KEY_DAILY", value).apply()



    private fun saveTheme(theme: Int) = sharedPreferences.edit().putInt("KEY_THEME", theme).apply()
    private fun getSavedTheme() = sharedPreferences.getInt("KEY_THEME", -1)

    private fun isEnabledHourlyForecast() = sharedPreferences.getBoolean("KEY_HOURLY", false)
    private fun isEnabledDailyForecast() = sharedPreferences.getBoolean("KEY_DAILY", true)

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}