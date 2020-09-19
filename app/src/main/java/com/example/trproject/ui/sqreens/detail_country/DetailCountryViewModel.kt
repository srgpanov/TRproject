package com.example.trproject.ui.sqreens.detail_country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trproject.data.models.CountryItem
import com.example.worldofhunting.other.MutableLiveDataKt

class DetailCountryViewModel(countryItem: CountryItem) : ViewModel() {
    val country = MutableLiveDataKt(countryItem)

    class Factory(private val countryItem: CountryItem) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return when {
                modelClass.isAssignableFrom(DetailCountryViewModel::class.java) -> {
                    DetailCountryViewModel(countryItem) as T
                }
                else -> throw IllegalStateException("wrong ViewModel")
            }
        }

    }
}
