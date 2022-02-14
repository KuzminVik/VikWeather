package ru.geekbrains.myweather

import android.content.SharedPreferences
import com.github.terrakok.cicerone.Router
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.geekbrains.myweather.date.weather.WeatherRepository
import ru.geekbrains.myweather.presentation.currentweather.CurrentWeatherPresenter
import ru.geekbrains.myweather.scheduler.ISchedulers
import ru.geekbrains.myweather.util.CITY_NAME

class CurrentWeatherPresenterTest {

    private lateinit var presenter: CurrentWeatherPresenter
    private val nameCity: String = "Новосибирск"
    @Mock
    private lateinit var repo: WeatherRepository
    @Mock
    private lateinit var router: Router
    @Mock
    private lateinit var schedulers: ISchedulers
    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = CurrentWeatherPresenter(nameCity, repo, router, schedulers, sharedPreferences)
    }

    /*
    Запуст теста завершается крэшкм.
    Mockito cannot mock this class: class com.github.terrakok.cicerone.Router.
    Underlying exception : org.mockito.exceptions.base.MockitoException: Could not modify all classes [class java.lang.Object, class com.github.terrakok.cicerone.Router, class com.github.terrakok.cicerone.BaseRouter]
	at ru.geekbrains.myweather.CurrentWeatherPresenterTest.setUp(CurrentWeatherPresenterTest.kt:30)
    ...
     */
    @Test
    fun saveNameCity_Test(){
        presenter.saveNameCity(nameCity)
        Mockito.verify(sharedPreferences, Mockito.times(1)).edit().putString(CITY_NAME, nameCity).apply()
    }
}