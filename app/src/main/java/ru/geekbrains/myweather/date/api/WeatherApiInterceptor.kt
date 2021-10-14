package ru.geekbrains.myweather.date.api

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

object WeatherApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request()
                .newBuilder()
                .header("Authorization", Credentials.basic(GITHUB_USER_NAME, GITHUB_USER_PASSWORD))
                .build()
        )

}