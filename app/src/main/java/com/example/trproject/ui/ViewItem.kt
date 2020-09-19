package com.example.trproject.ui

import androidx.viewbinding.ViewBinding

interface ViewItem<VB : ViewBinding> {
    fun bind(binding: VB)
}