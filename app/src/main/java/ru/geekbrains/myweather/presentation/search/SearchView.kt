package ru.geekbrains.myweather.presentation.search

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface SearchView: MvpView {

    @SingleState
    fun showErrorCity(message: String)

}