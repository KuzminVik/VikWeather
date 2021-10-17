package ru.geekbrains.myweather.date.weather

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

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
    @ColumnInfo(name = "icon") val icon: String              //URL is http://openweathermap.org/img/wn/10d@2x.png
)

data class AllWeather(
    @SerializedName("current")
    val current: Current
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
