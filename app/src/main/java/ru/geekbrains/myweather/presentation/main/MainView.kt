package ru.geekbrains.myweather.presentation.main

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface MainView: MvpView {

    @SingleState
    fun getNameCity()
}