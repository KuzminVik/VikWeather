package ru.geekbrains.myweather.di

import javax.inject.Qualifier

@Qualifier
annotation class InMemory

@Qualifier
annotation class Persisted