package ru.geekbrains.myweather.presentation.search

import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

class SearchPresenter: MvpPresenter<SearchView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {

    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()

    }

}
