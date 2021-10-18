package ru.geekbrains.myweather.presentation.currentweather

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.geekbrains.myweather.date.weather.WeatherEntity

interface CurrentWeatherView: MvpView {

    /**
     * Показывает погоду на главном экране
     */
    @SingleState
    fun showCurrentWeather(weatherEntity: WeatherEntity)

    /**
     * Обрабатывает ошибку получения погодных данных
     */
    @SingleState
    fun showErrorCurrentWeather(e: Throwable?)

    @SingleState
    fun saveNameCity(name: String)

    @SingleState
    fun setTitle(title: String)

}