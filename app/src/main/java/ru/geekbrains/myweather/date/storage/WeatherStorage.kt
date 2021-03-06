package ru.geekbrains.myweather.date.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.myweather.date.weather.models.City
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.date.weather.models.HourEntity
import ru.geekbrains.myweather.date.weather.models.WeatherEntity

@Database(
    exportSchema = false,
    entities = [WeatherEntity::class, DayEntity::class, HourEntity::class, City::class], version = 2
)
abstract class WeatherStorage: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}