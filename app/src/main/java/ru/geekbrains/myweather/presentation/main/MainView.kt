package ru.geekbrains.myweather.presentation.main

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface MainView: MvpView {

    /**
     * Получить название последнего города, для которого запрашивалась погода
     */
    @SingleState
    fun getNameCity()
}