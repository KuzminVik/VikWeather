package ru.geekbrains.myweather.date.weather.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weathers")
data class WeatherEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "lon") val lon: Double,
    @ColumnInfo(name = "dt") val dt: Long,
    @ColumnInfo(name = "sunrise") val sunrise: Long,
    @ColumnInfo(name = "sunset") val sunset: Long,
    @ColumnInfo(name = "temp") val temp: Double,
    @ColumnInfo(name = "feelsLike") val feelsLike: Double,
    @ColumnInfo(name = "pressure") val pressure: Int,
    @ColumnInfo(name = "humidity") val humidity: Int,
    @ColumnInfo(name = "dewPoint") val dewPoint: Double,
    @ColumnInfo(name = "uvi") val uvi: Double,
    @ColumnInfo(name = "clouds") val clouds: Int,
    @ColumnInfo(name = "visibility") val visibility: Int,
    @ColumnInfo(name = "windSpeed") val windSpeed: Double,
    @ColumnInfo(name = "windDeg") val windDeg: Double,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "icon") val icon: String,              //URL is http://openweathermap.org/img/wn/10d@2x.png
)

@Entity(tableName = "daily")
data class DayEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "parent_name") val parentName: String,
    @ColumnInfo(name = "dt") val dt: Long,
    @ColumnInfo(name = "temp_day") val tempDay: Double,
    @ColumnInfo(name = "temp_night") val tempNight: Double,
    @ColumnInfo(name = "pressure") val pressure: Int,
    @ColumnInfo(name = "humidity") val humidity: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "icon") val icon: String
)