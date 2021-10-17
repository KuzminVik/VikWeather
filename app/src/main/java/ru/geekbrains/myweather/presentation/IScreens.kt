package ru.geekbrains.myweather.presentation

import com.github.terrakok.cicerone.Screen

interface IScreens {

    /**
     * Экран текущей погоды (главный)
     */
    fun current(name: String): Screen

    /**
     * Экран настроек приложения
     */
    fun setting(): Screen

    /**
     * Экран поиска прогноза погоды по городу, последние просмотренные города
     */
    fun search(): Screen

}