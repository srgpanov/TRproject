package com.example.trproject.ui.sqreens.detail_country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trproject.data.models.CountryItem
import com.example.trproject.databinding.FragmentDetailCountryBinding
import com.example.worldofhunting.other.InsetSide
import com.example.worldofhunting.other.loadSvgOrOthers
import com.example.worldofhunting.other.setHeightOrWidthAsSystemWindowInset

class DetailCountryFragment : Fragment() {
    private var _binding: FragmentDetailCountryBinding? = null
    private val binding: FragmentDetailCountryBinding
        get() = _binding!!


    private lateinit var viewModel: DetailCountryViewModel

    companion object {
        private const val KEY_COUNTRY = "KEY_COUNTRY"
        fun newInstance(country: CountryItem): DetailCountryFragment {
            return DetailCountryFragment().apply {
                arguments = bundleOf(KEY_COUNTRY to country)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val argsCountry = arguments?.getParcelable<CountryItem>(KEY_COUNTRY)
            ?: throw IllegalStateException("Fragments args not contains country")
        val factory = DetailCountryViewModel.Factory(argsCountry)
        viewModel = ViewModelProvider(this, factory)[DetailCountryViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInsets()
        observeViewVodel()
    }

    private fun setupInsets() {
        binding.viewStatus.setHeightOrWidthAsSystemWindowInset(InsetSide.TOP)
    }

    private fun observeViewVodel() {
        viewModel.country.observe(viewLifecycleOwner) { country ->
            bindCountry(country)
        }
    }

    private fun bindCountry(country: CountryItem) = with(binding) {
        ivFlag.loadSvgOrOthers(country.flag)
        tvName.text = country.name
        tvCurrency.text = country.currencies?.joinToString(separator = ", ") { it.name }
        tvLanguage.text = country.languages?.joinToString(separator = ", ") { it.name }
        tvTimeZone.text = country.timezones?.joinToString (separator = "\n")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
