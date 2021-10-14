package ru.geekbrains.myweather.presentation.settings

import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

class SettingsPresenter: MvpPresenter<SettingsView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {

    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()

    }

}