package com.example.trproject.di

import android.os.Bundle
import androidx.lifecycle.ViewModel

interface ViewModelAssistedFactory<T : ViewModel> {
    fun create(arguments: Bundle): T
}