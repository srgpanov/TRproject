package com.example.trproject.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trproject.ui.sqreens.country_list.CountryListViewModel
import com.example.trproject.ui.sqreens.detail_country.DetailCountryViewModel
import com.example.trproject.di.ViewModelFactory
import com.example.trproject.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // TODO: 16.09.2020 раскоментировать 
//    @Binds
//    @IntoMap
//    @ViewModelKey(DetailCountryViewModel::class)
//    internal abstract fun detailCountryViewModel(viewModel: DetailCountryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountryListViewModel::class)
    internal abstract fun countryListViewModel(viewModel: CountryListViewModel): ViewModel

}