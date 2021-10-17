package ru.geekbrains.myweather.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.geekbrains.myweather.date.weather.WeatherRepository
import ru.geekbrains.myweather.date.weather.WeatherRepositoryImpl
import ru.geekbrains.myweather.date.weather.datasource.CacheDataSource
import ru.geekbrains.myweather.date.weather.datasource.CacheDataSourceImpl
import ru.geekbrains.myweather.date.weather.datasource.NetworkDataSource
import ru.geekbrains.myweather.date.weather.datasource.NetworkDataSourceImpl
import ru.geekbrains.myweather.presentation.currentweather.CurrentWeatherFragment
import ru.geekbrains.myweather.presentation.main.MainActivity
import ru.geekbrains.myweather.presentation.search.SearchFragment
import ru.geekbrains.myweather.presentation.settings.SettingsFragment
import javax.inject.Singleton

@Module
interface WeatherModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindWeatherFragment(): CurrentWeatherFragment

    @ContributesAndroidInjector
    fun bindSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    fun bindSettingsFragment(): SettingsFragment

    @Singleton
    @Binds
    fun bindGitHubUserRepository(repository: WeatherRepositoryImpl): WeatherRepository

    @Singleton
    @Binds
    fun bindUserDataSource(networkDataSource: NetworkDataSourceImpl): NetworkDataSource

    @Singleton
    @Binds
    fun bindCacheUserDataSource(cacheDataSource: CacheDataSourceImpl): CacheDataSource

}