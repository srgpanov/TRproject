package com.example.trproject.data.remote

import com.example.trproject.data.local.CountryDao
import com.example.trproject.data.models.CountryList
import com.example.trproject.data.remote.CountryApi.Fields.DEFAULT_QUERY
import com.example.trproject.data.remote.call_adapter.ResponseResult
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val countryApi: CountryApi,
    private val countryDao: CountryDao
) {
    suspend fun getCountry(fields: String = DEFAULT_QUERY): ResponseResult<CountryList> =
        countryApi.getCountry(fields)


    suspend fun getAndSaveCountry(fields: String = DEFAULT_QUERY): ResponseResult<CountryList> =
        coroutineScope {
            val countryResponse = getCountry(fields)
            if (countryResponse is ResponseResult.Success) {
                launch { countryDao.insertOrUpdate(countryResponse.data) }
            }
            countryResponse
        }
}