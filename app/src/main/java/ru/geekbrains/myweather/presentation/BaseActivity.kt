package ru.geekbrains.myweather.presentation

import com.github.terrakok.cicerone.Cicerone
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import ru.geekbrains.myweather.di.DaggerBaseActivityComponent
import ru.geekbrains.myweather.scheduler.MainSchedulers

class BaseActivity : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<BaseActivity> =

        DaggerBaseActivityComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
                withSchedulers(MainSchedulers())
            }
            .build()

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { }
    }
}