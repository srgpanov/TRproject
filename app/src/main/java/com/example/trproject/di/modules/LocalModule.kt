package com.example.trproject.di.modules

import android.content.Context
import androidx.room.Room
import com.example.trproject.data.local.CountryDao
import com.example.trproject.data.local.CountryDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): CountryDataBase {
        return Room
            .databaseBuilder(context, CountryDataBase::class.java, "countryDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(dataBase: CountryDataBase): CountryDao {
        return dataBase.newsDao()
    }


}