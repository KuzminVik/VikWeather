package ru.geekbrains.myweather.presentation.search

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.geekbrains.myweather.date.weather.models.City

interface SearchView: MvpView {

    /**
     * Показать сообщение о неудачном запросе
     */
    @SingleState
    fun showErrorCity(message: String)

    /**
     * Показать список последних запросов
     * @param list Список городов
     */
    @SingleState
    fun showListCity(list: List<City>)

    /**
     * Скрывает список последних запросов
     */
    @SingleState
    fun hideListCity()

}