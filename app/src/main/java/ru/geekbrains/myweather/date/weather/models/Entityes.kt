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
    @ColumnInfo(name = "temp") val temp: String,
    @ColumnInfo(name = "feelsLike") val feelsLike: String,
    @ColumnInfo(name = "temp_day") val tempDay: String,
    @ColumnInfo(name = "temp_night") val tempNight: String,
    @ColumnInfo(name = "temp_eve") val tempEve: String,
    @ColumnInfo(name = "temp_morn") val tempMorn: String,
    @ColumnInfo(name = "pressure") val pressure: String,
    @ColumnInfo(name = "humidity") val humidity: String,
    @ColumnInfo(name = "dewPoint") val dewPoint: String,
    @ColumnInfo(name = "uvi") val uvi: String,
    @ColumnInfo(name = "clouds") val clouds: String,
    @ColumnInfo(name = "visibility") val visibility: String,
    @ColumnInfo(name = "windSpeed") val windSpeed: String,
    @ColumnInfo(name = "windDeg") val windDeg: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "icon") val icon: String              //URL is https://openweathermap.org/img/wn/10d@2x.png
)

@Entity(tableName = "daily")
data class DayEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "parent_name") val parentName: String,
    @ColumnInfo(name = "dt") val dt: Long,
    @ColumnInfo(name = "temp_day") val tempDay: String,
    @ColumnInfo(name = "temp_night") val tempNight: String,
    @ColumnInfo(name = "temp_eve") val tempEve: String,
    @ColumnInfo(name = "temp_morn") val tempMorn: String,
    @ColumnInfo(name = "pressure") val pressure: String,
    @ColumnInfo(name = "humidity") val humidity: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "icon") val icon: String
)

@Entity(tableName = "hourly")
data class HourEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "parent_name") val parentName: String,
    @ColumnInfo(name = "dt") val dt: Long,
    @ColumnInfo(name = "temp") val temp: String,
    @ColumnInfo(name = "pressure") val pressure: String,
    @ColumnInfo(name = "humidity") val humidity: String,
    @ColumnInfo(name = "clouds") val clouds: String,
    @ColumnInfo(name = "visibility") val visibility: String,
    @ColumnInfo(name = "windSpeed") val windSpeed: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "icon") val icon: String
)

@Entity(tableName = "city")
data class City(
    @PrimaryKey()
    @ColumnInfo(name = "name") val name: String
)