package ru.geekbrains.myweather.date.weather.models

data class WeatherModel(
    val weatherEntity: WeatherEntity,
    val daily: List<DayEntity>
//    val hourly: List<HourEntity>
)