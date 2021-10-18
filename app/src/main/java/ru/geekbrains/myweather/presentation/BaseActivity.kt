package ru.geekbrains.myweather.presentation

import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Cicerone
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
//                withActivity(activity as AppCompatActivity)
            }
            .build()

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { }
    }
}