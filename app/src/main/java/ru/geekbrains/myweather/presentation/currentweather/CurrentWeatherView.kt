package ru.geekbrains.myweather.presentation.currentweather

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.date.weather.models.WeatherEntity

interface CurrentWeatherView: MvpView {

    /**
     * Показывает текущую погоду на главном экране
     */
    @SingleState
    fun showCurrentWeather(weatherEntity: WeatherEntity)

    /**
     * Обрабатывает ошибку получения погодных данных
     */
    @SingleState
    fun showErrorCurrentWeather(e: Throwable?)

    /**
     * Показывает прогноз погоды на неделю
     */
    @SingleState
    fun showDaily(list: List<DayEntity>)

    /**
     * Изменяет текст тулбара при переходе с экрана поиска
     */
    @SingleState
    fun setTitle(title: String)

}