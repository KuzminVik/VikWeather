package ru.geekbrains.myweather.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweather.databinding.FragmentSearchBinding
import ru.geekbrains.myweather.presentation.abs.AbsFragment
import ru.geekbrains.myweather.R.layout.fragment_search
import ru.geekbrains.myweather.date.weather.WeatherRepository
import ru.geekbrains.myweather.presentation.arguments
import ru.geekbrains.myweather.presentation.click
import javax.inject.Inject

class SearchFragment : AbsFragment(fragment_search), SearchView {

    private val viewBinding: FragmentSearchBinding by viewBinding()

    companion object {
        private const val TAG = "SearchFragment"
        fun newInstance(): Fragment =
            SearchFragment().arguments()
    }

    @Inject
    lateinit var repo: WeatherRepository

    private val presenter: SearchPresenter by moxyPresenter {
        SearchPresenter(
            repo = repo,
            router = router,
            schedulers = schedulers,
        ) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.button4.click{
            val city = viewBinding.tiEditText.text.toString()
            presenter.clickCity(city)
        }
    }

    override fun showErrorCity(message: String) {
        Log.d(TAG, message)
        Toast.makeText(requireContext(),"ERROR ${TAG}: $message", Toast.LENGTH_LONG).show()
    }

}