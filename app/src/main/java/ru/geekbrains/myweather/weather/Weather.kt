package ru.geekbrains.myweather.weather

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class AllWeather(
    val lat: Double,
    val lon: Double,
    val timezone_offset: Long, //Сдвиг в секундах от UTC
    val current: Current
)

data class Weather(
    @SerializedName("id")
    val id: String,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)

data class Current(
    @PrimaryKey
    @SerializedName("dt")
    @ColumnInfo(name = "login")
    val dt: Long, //Текущее время, Unix, UTC
    @SerializedName("sunrise")
    @ColumnInfo(name = "sunrise")
    val sunrise: Long, // время восхода, Unix, UTC
    @SerializedName("sunset")
    @ColumnInfo(name = "sunset")
    val sunset: Long, // время заката, Unix, UTC
    @SerializedName("temp")
    @ColumnInfo(name = "temp")
    val temp: Double,
    @SerializedName("feels_like")
    @ColumnInfo(name = "feelsLike")
    val feelsLike: Double,
    @SerializedName("pressure")
    @ColumnInfo(name = "pressure")
    val pressure: Int,
    @SerializedName("humidity")
    @ColumnInfo(name = "humidity")
    val humidity: Int,
    @SerializedName("dew_point")
    @ColumnInfo(name = "dewPoint")
    val dewPoint: Double, // Точка росы
    @SerializedName("uvi")
    @ColumnInfo(name = "uvi")
    val uvi: Double, // Уф-индекс
    @SerializedName("clouds")
    @ColumnInfo(name = "clouds")
    val clouds: Int,
    @SerializedName("visibility")
    @ColumnInfo(name = "visibility")
    val visibility: Int,
    @SerializedName("wind_speed")
    @ColumnInfo(name = "windSpeed")
    val windSpeed: Int,
    @SerializedName("wind_deg")
    @ColumnInfo(name = "windDeg")
    val windDeg: Int,
    @SerializedName("weather")
    @ColumnInfo(name = "weather")
    val weather: Weather
)

