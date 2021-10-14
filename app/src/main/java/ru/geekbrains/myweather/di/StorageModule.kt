package ru.geekbrains.myweather.di

import android.content.Context
import androidx.room.Room
import dagger.Provides
import javax.inject.Singleton

class StorageModule {

    @Singleton
    @Persisted
    @Provides
    fun provideWeatherDatabaseStorage(context: Context): GitHubStorage =
        Room.databaseBuilder(context, GitHubStorage::class.java, "weather.db")
            .fallbackToDestructiveMigration()
//            .addMigrations(GitHub1to2Migration, GitHub2to3Migration)
            .build()

    @InMemory
    @Provides
    fun provideWeatherInMemoryDatabaseStorage(context: Context): GitHubStorage =
        Room.inMemoryDatabaseBuilder(context, GitHubStorage::class.java)
            .fallbackToDestructiveMigration()
            .build()

}