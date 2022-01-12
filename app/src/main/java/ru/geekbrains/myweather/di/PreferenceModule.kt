package ru.geekbrains.myweather.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Singleton
    @Provides
    fun providesSharedPref(context: Context): SharedPreferences {
        return context.applicationContext.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)
    }

}