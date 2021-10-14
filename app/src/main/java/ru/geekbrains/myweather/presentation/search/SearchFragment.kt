package ru.geekbrains.myweather.presentation.search

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweather.databinding.FragmentSearchBinding
import ru.geekbrains.myweather.presentation.abs.AbsFragment
import ru.geekbrains.myweather.R.layout.fragment_search
import ru.geekbrains.myweather.presentation.arguments

class SearchFragment : AbsFragment(fragment_search), SearchView {

    private val viewBinding: FragmentSearchBinding by viewBinding()

    companion object {
        private const val TAG = "SearchFragment"
        fun newInstance(): Fragment =
            SearchFragment().arguments()
    }

    private val presenter: SearchPresenter by moxyPresenter {
        SearchPresenter() }
}