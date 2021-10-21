package ru.geekbrains.myweather.date.weather

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class AllWeather(
    @SerializedName("current")
    val current: Current,
    @SerializedName("daily")
    val daily: List<Day>
)

data class Current(
    @SerializedName("dt") val dt: Long,                    //Текущее время, Unix, UTC
    @SerializedName("sunrise") val sunrise: Long,          // время восхода, Unix, UTC
    @SerializedName("sunset") val sunset: Long,            // время заката, Unix, UTC
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("dew_point") val dewPoint: Double,     // Точка росы
    @SerializedName("uvi") val uvi: Double,                // Уф-индекс
    @SerializedName("clouds") val clouds: Int,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_deg") val windDeg: Double,
    @SerializedName("weather") val weather: List<Weather>
)

data class Weather(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class Day(
    @SerializedName("dt") val dt: Long,
    @SerializedName("temp") val temp: Temp,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("weather") val weather: List<Weather>
)

data class Temp(
    @SerializedName("day") val day: Double,                 // Температура днем
    @SerializedName("night") val night: Double              // Температура ночью
)

data class Location(
    @SerializedName("coord") val coord: Coord,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class Coord(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
)