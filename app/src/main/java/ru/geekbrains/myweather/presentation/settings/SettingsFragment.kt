package ru.geekbrains.myweather.presentation.settings

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweather.databinding.FragmentSettingsBinding
import ru.geekbrains.myweather.presentation.abs.AbsFragment
import ru.geekbrains.myweather.presentation.arguments
import ru.geekbrains.myweather.presentation.search.SearchView
import ru.geekbrains.myweather.R.layout.fragment_settings

class SettingsFragment : AbsFragment(fragment_settings), SettingsView {

    private val viewBinding: FragmentSettingsBinding by viewBinding()

    companion object {
        private const val TAG = "SettingsFragment"
        fun newInstance(): Fragment =
            SettingsFragment().arguments()
    }

    private val presenter: SettingsPresenter by moxyPresenter {
        SettingsPresenter() }
}