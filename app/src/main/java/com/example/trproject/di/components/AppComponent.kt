package com.example.trproject.di.components

import com.example.trproject.di.modules.ViewModelsModule
import com.example.trproject.di.modules.AppModule
import com.example.trproject.di.modules.LocalModule
import com.example.trproject.ui.sqreens.country_list.CountryListFragment
import com.example.trproject.di.modules.RemoteModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    RemoteModule::class,
    LocalModule::class,
    ViewModelsModule::class])
interface AppComponent {
    fun injectCountryListFragment(fragment: CountryListFragment)


}