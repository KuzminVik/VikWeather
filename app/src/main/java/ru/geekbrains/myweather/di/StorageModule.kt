package ru.geekbrains.myweather.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.myweather.date.storage.WeatherStorage
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideWeatherDatabaseStorage(context: Context): WeatherStorage =
        Room.databaseBuilder(context, WeatherStorage::class.java, "weather.db")
            .fallbackToDestructiveMigration()
//            .addMigrations(GitHub1to2Migration, GitHub2to3Migration)
            .build()

}