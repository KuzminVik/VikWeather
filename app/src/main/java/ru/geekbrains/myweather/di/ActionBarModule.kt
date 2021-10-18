package ru.geekbrains.myweather.di

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActionBarModule {

    @Singleton
    @Provides
    fun providesActivity(activity: AppCompatActivity): ActionBar {
        return activity.supportActionBar!!

    }
}