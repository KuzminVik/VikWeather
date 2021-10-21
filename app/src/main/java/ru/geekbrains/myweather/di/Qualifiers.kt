package ru.geekbrains.myweather.di

import javax.inject.Qualifier

@Qualifier
annotation class DbInMemory

@Qualifier
annotation class DbPersisted

//@Qualifier
//annotation class PrefCity
//
//@Qualifier
//annotation class PrefTheme