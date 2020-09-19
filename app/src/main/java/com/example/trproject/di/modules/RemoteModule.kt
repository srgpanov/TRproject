package com.example.trproject.di.modules

import androidx.viewbinding.BuildConfig
import com.example.trproject.data.remote.CountryApi
import com.example.trproject.data.remote.call_adapter.ResponseResultAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {
    companion object {
        private const val BASE_NEWS_URL = "https://restcountries.eu/"
    }

    @Singleton
    @Provides
    fun getLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
        }
        return loggingInterceptor
    }


    @Singleton
    @Provides
    fun getHttpClient(
        loggingInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun createHeadlineNewsService(httpClient: OkHttpClient): CountryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_NEWS_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResponseResultAdapterFactory())
            .build()
            .create(CountryApi::class.java)
    }
}