package ru.geekbrains.myweather.presentation.abs

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import ru.geekbrains.myweather.scheduler.ISchedulers
import javax.inject.Inject

abstract class AbsFragment(@LayoutRes contentLayoutId: Int) : MvpAppCompatFragment(contentLayoutId),
    HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var schedulers: ISchedulers

    var actionBar: ActionBar? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        actionBar = (activity as AppCompatActivity).supportActionBar
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

}