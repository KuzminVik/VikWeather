package ru.geekbrains.myweather.presentation.currentweather

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.SingleState
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.date.weather.models.HourEntity
import ru.geekbrains.myweather.date.weather.models.WeatherEntity

interface CurrentWeatherView: MvpView {

    /**
     * Показывает текущую погоду на главном экране
     * @param weatherEntity Данные о текущей погоде
     */
    @AddToEndSingle
    fun showCurrentWeather(weatherEntity: WeatherEntity)

    /**
     * Обрабатывает ошибку получения погодных данных
     */
    @AddToEndSingle
    fun showErrorCurrentWeather(e: Throwable?)

    /**
     * Показывает прогноз погоды на неделю
     * @param list Данные погоды на ближайшие 7 дней
     */
    @AddToEnd
    fun showDaily(list: List<DayEntity>)

    /**
     * Показывает почасовой прогноз погоды
     * @param data Список данных по погоде на ближайшие 48 часов, с логическим флагом показа подробностей
     */
    @AddToEnd
    fun showHourly(data: List<Pair<HourEntity, Boolean>>)

    /**
     * Изменяет текст тулбара при переходе с экрана поиска
     * @param title Текст
     */
    @AddToEnd
    fun setTitle(title: String)

}