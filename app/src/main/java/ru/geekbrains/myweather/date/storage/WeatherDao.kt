package ru.geekbrains.myweather.date.storage

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import ru.geekbrains.myweather.date.weather.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weathers WHERE name LIKE :name LIMIT 1")
    fun getWeatherByName(name: String): Single<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retainWeather(weatherEntity: WeatherEntity): Completable

    @Delete()
    fun deleteWeatherByName(weatherEntity: WeatherEntity): Completable

}