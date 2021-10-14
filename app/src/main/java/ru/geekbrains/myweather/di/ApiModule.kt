package ru.geekbrains.myweather.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.myweather.date.api.WeatherApi
import ru.geekbrains.myweather.date.api.WeatherApiInterceptor
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideApi(): WeatherApi =
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(WeatherApiInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WeatherApi::class.java)

    private val gson: Gson =
        GsonBuilder()
            .create()
}