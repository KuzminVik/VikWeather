package ru.geekbrains.myweather.presentation.settings

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.SingleState

interface SettingsView: MvpView {

    @SingleState
    fun initTheme(theme: Int)

    @AddToEndSingle
    fun initHourlySettings(boolean: Boolean)

    @AddToEndSingle
    fun initDailySettings(boolean: Boolean)
}