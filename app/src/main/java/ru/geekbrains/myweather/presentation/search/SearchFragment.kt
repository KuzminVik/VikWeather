package ru.geekbrains.myweather.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweather.R
import ru.geekbrains.myweather.databinding.FragmentSearchBinding
import ru.geekbrains.myweather.presentation.abs.AbsFragment
import ru.geekbrains.myweather.R.layout.fragment_search
import ru.geekbrains.myweather.date.weather.WeatherRepository
import ru.geekbrains.myweather.date.weather.models.City
import ru.geekbrains.myweather.presentation.arguments
import ru.geekbrains.myweather.presentation.click
import ru.geekbrains.myweather.presentation.search.adapter.SearchAdapter
import ru.geekbrains.myweather.util.ERROR_NAME_CITY
import ru.geekbrains.myweather.util.TAG_SEARCH
import javax.inject.Inject

class SearchFragment : AbsFragment(fragment_search), SearchView, SearchAdapter.ClickCityDelegat {

    private val viewBinding: FragmentSearchBinding by viewBinding()
    private val nameCityValidator = ValidateInputText()
    private val searchAdapter = SearchAdapter(this)

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
        viewBinding.rvListCity.adapter = searchAdapter
        viewBinding.tiEditText.addTextChangedListener(nameCityValidator)
        viewBinding.button4.click{
            if (nameCityValidator.isValid) presenter.clickCity(viewBinding.tiEditText.text.toString())
            else showErrorCity(ERROR_NAME_CITY)
        }
    }

    override fun showErrorCity(message: String) {
        Log.d(TAG, message)
        Toast.makeText(requireContext(),"${getString(R.string.error)} ${TAG}: $message", Toast.LENGTH_LONG).show()
    }

    override fun showListCity(list: List<City>){
        viewBinding.tvLabelListCity.visibility = if(list.isEmpty()) View.GONE else View.VISIBLE
        viewBinding.rvListCity.visibility = View.VISIBLE
        searchAdapter.submitList(list)
    }

    override fun hideListCity(){
        viewBinding.tvLabelListCity.visibility = View.GONE
        viewBinding.rvListCity.visibility = View.GONE
    }

    companion object {
        private const val TAG = TAG_SEARCH
        fun newInstance(): Fragment =
            SearchFragment()
    }

    override fun clickCity(name: String) {
        presenter.clickCity(name)
    }
}