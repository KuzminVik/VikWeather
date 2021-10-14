package ru.geekbrains.myweather.presentation.main

import moxy.MvpView

interface MainView: MvpView {

    fun getLocation(): Pair<Double, Double>
}