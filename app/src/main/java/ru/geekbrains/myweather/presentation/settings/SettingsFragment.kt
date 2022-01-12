package ru.geekbrains.myweather.presentation.settings

import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweather.R
import ru.geekbrains.myweather.databinding.FragmentSettingsBinding
import ru.geekbrains.myweather.presentation.abs.AbsFragment
import ru.geekbrains.myweather.R.layout.fragment_settings
import ru.geekbrains.myweather.util.TAG_SETTINGS
import ru.geekbrains.myweather.util.THEME_DARK
import ru.geekbrains.myweather.util.THEME_LIGHT
import ru.geekbrains.myweather.util.THEME_UNDEFINED
import javax.inject.Inject

class SettingsFragment : AbsFragment(fragment_settings), SettingsView {

    private val viewBinding: FragmentSettingsBinding by viewBinding()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val presenter: SettingsPresenter by moxyPresenter {
        SettingsPresenter(sharedPreferences = sharedPreferences) }

    override fun initTheme(theme: Int) {
        when (theme) {
            THEME_LIGHT -> viewBinding.themeLight.isChecked = true
            THEME_DARK -> viewBinding.themeDark.isChecked = true
            THEME_UNDEFINED -> {
                when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
                    Configuration.UI_MODE_NIGHT_NO -> viewBinding.themeLight.isChecked = true
                    Configuration.UI_MODE_NIGHT_YES -> viewBinding.themeDark.isChecked = true
                    Configuration.UI_MODE_NIGHT_UNDEFINED -> viewBinding.themeLight.isChecked = true
                }
            }
        }
        viewBinding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.themeLight -> presenter.setTheme(AppCompatDelegate.MODE_NIGHT_NO, THEME_LIGHT)
                R.id.themeDark -> presenter.setTheme(AppCompatDelegate.MODE_NIGHT_YES, THEME_DARK)
            }
        }
    }

    override fun initHourlySettings(boolean: Boolean) {
        viewBinding.switch1.apply {
            setOnCheckedChangeListener { _, isChecked ->
                presenter.enabledHourlyForecast(isChecked)
            }
            isChecked = boolean
        }
    }

    override fun initDailySettings(boolean: Boolean) {
        viewBinding.switch2.apply {
            setOnCheckedChangeListener { _, isChecked ->
            presenter.enabledDailyForecast(isChecked)
        }
            isChecked = boolean
        }
    }

    companion object {
        private const val TAG = TAG_SETTINGS
        fun newInstance(): Fragment =
            SettingsFragment()
    }
}