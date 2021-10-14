package ru.geekbrains.myweather.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.reactivex.schedulers.Schedulers
import ru.geekbrains.myweather.presentation.BaseActivity
import ru.geekbrains.myweather.scheduler.SchedulersThreads
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [AndroidInjectionModule::class, ApiModule::class, StorageModule::class]
)

interface BaseActivityComponent : AndroidInjector<BaseActivity> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withSchedulers(schedulers: SchedulersThreads): Builder

        fun build(): BaseActivityComponent
    }

}