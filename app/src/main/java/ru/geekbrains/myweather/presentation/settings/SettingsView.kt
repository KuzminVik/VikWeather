package ru.geekbrains.myweather.presentation.settings

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface SettingsView: MvpView {

    @SingleState
    fun initThemeListener()

    @SingleState
    fun initTheme(theme: Int)

    @SingleState
    fun initHourlySettings(boolean: Boolean)

    @SingleState
    fun initDailySettings(boolean: Boolean)
}