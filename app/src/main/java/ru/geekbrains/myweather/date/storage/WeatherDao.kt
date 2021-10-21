package ru.geekbrains.myweather.date.storage

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.date.weather.models.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weathers WHERE name LIKE :name LIMIT 1")
    fun getWeatherByName(name: String): Single<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weatherEntity: WeatherEntity): Completable

    @Delete()
    fun deleteWeather(weatherEntity: WeatherEntity): Completable

    @Query("DELETE FROM weathers WHERE name LIKE :name")
    fun deleteWeatherByName(name: String): Completable

    // ==========================================================

    @Query("SELECT * FROM daily WHERE parent_name LIKE :name")
    fun getDailyByName(name: String): Single<List<DayEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDaily(daily: List<DayEntity>): Completable

    @Delete()
    fun deleteDaily(daily: List<DayEntity>): Completable

    @Query("DELETE FROM daily WHERE parent_name LIKE :name")
    fun deleteDailyByName(name: String): Completable

}