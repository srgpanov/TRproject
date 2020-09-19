package com.example.trproject.data.remote

import com.example.trproject.data.models.CountryList
import com.example.trproject.data.remote.call_adapter.ResponseResult
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryApi {
    companion object Fields{
        const val NAME = "name"
        const val FLAG = "flag"
        const val CURRENCIES = "currencies"
        const val LANGUAGES = "languages"
        const val TIMEZONES = "timezones"
        const val DEFAULT_QUERY = "$NAME;$FLAG;$CURRENCIES;$LANGUAGES;$TIMEZONES"
    }

    @GET("rest/v2")
    suspend fun getCountry(
        @Query("fields") fields: String = DEFAULT_QUERY
    ): ResponseResult<CountryList>
}