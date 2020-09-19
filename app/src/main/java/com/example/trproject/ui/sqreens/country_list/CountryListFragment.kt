package com.example.trproject.ui.sqreens.country_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trproject.App
import com.example.trproject.R
import com.example.trproject.data.models.CountryItem
import com.example.trproject.databinding.FragmentCountryListBinding
import com.example.trproject.ui.sqreens.detail_country.DetailCountryFragment
import com.example.worldofhunting.other.InsetSide
import com.example.worldofhunting.other.addSystemWindowInsetToPadding
import com.example.worldofhunting.other.setHeightOrWidthAsSystemWindowInset
import javax.inject.Inject


class CountryListFragment : Fragment() {
    private var _binding: FragmentCountryListBinding? = null
    private val binding: FragmentCountryListBinding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CountryListViewModel
    private val adapter by lazy { CountryAdapter() }


    companion object {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.injectCountryListFragment(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[CountryListViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInsets()
        setupRV()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(viewLifecycleOwner) { countryList ->
            adapter.setItems(countryList)
        }
        viewModel.errorEvent.observe(viewLifecycleOwner) { errorMassage ->
            Toast.makeText(requireContext(), getString(R.string.error_prefix) +
                    "\n$errorMassage", Toast.LENGTH_LONG).show()
        }
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.swipeLayout.isRefreshing = isLoading
        }
    }

    private fun setupRV() {
        binding.rv.adapter = adapter
        binding.swipeLayout.setOnRefreshListener {
            viewModel.fetchCountries()
        }
        adapter.clickListener = { country ->
            navigateToDetailFragment(country)
        }
    }

    private fun navigateToDetailFragment(country: CountryItem) {
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, DetailCountryFragment.newInstance(country))
            .addToBackStack(null)
            .commit()
    }

    private fun setupInsets() = with(binding) {
        rv.addSystemWindowInsetToPadding(top = true, bottom = true)
        viewStatus.setHeightOrWidthAsSystemWindowInset(InsetSide.TOP)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
