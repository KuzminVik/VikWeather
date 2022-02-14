package ru.geekbrains.myweather

import android.content.SharedPreferences
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.geekbrains.myweather.presentation.settings.SettingsPresenter
import ru.geekbrains.myweather.util.KEY_HOURLY

class SettingsPresenterTest {
    private lateinit var presenter: SettingsPresenter

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = SettingsPresenter(sharedPreferences)
    }

    @Test
    fun enabledHourlyForecast_Test(){
        val value = false
        presenter.enabledDailyForecast(value)
        Mockito.verify(sharedPreferences, Mockito.times(1)).edit().putBoolean(KEY_HOURLY, value).apply()
    }
}