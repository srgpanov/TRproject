package com.example.trproject.di

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Suppress("UNCHECKED_CAST")
class ArgumentsViewModelFactory<out V : ViewModel>(
    private val viewModelFactory: ViewModelAssistedFactory<V>,
    private val arguments: Bundle
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
//            modelClass.isAssignableFrom(NewsListViewModel::class.java) -> {
//                viewModelFactory.create(arguments) as T
//            }
            else -> throw IllegalStateException("wrong ViewModel")
        }
    }
}