package com.example.trproject.ui.sqreens.country_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trproject.data.local.CountryDao
import com.example.trproject.data.models.CountryList
import com.example.trproject.data.models.CountryItem
import com.example.trproject.data.remote.RemoteRepository
import com.example.trproject.data.remote.call_adapter.ResponseResult
import com.example.worldofhunting.other.MutableLiveDataKt
import com.example.worldofhunting.other.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryListViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val dao: CountryDao
) :
    ViewModel() {
    val countries = MutableLiveDataKt<List<CountryItem>>(emptyList())
    val errorEvent: SingleLiveEvent<String> = SingleLiveEvent()
    val loading =MutableLiveDataKt(false)

    init {
        loadSavedCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch {
            loading.value = true
            val countryResponse = remoteRepository.getAndSaveCountry()
            when (countryResponse) {
                is ResponseResult.Success ->
                    processSuccessResponse(countryResponse.data)
                is ResponseResult.Failure.ServerError ->
                    errorEvent.value = countryResponse.errorBody?.toString()
                is ResponseResult.Failure.NetworkError ->
                    errorEvent.value = countryResponse.ex.toString()
            }
            loading.value = false
        }
    }

    private fun loadSavedCountries() {
        viewModelScope.launch {
            loading.value = true
            val countryList = dao.getAll()
            if (countryList.isEmpty()) {
                fetchCountries()
            } else {
                countries.value = fillCountries(countryList = countryList)
            }
            loading.value = false
        }
    }

    private fun processSuccessResponse(countryList: CountryList) {
        countries.value = fillCountries(countryList)
        // TODO: 16.09.2020 make mapping
    }

    private fun fillCountries(countryList: List<CountryItem>): List<CountryItem> {
        return countryList

    }
}
