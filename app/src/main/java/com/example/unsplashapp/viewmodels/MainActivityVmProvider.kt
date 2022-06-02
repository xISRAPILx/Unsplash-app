package com.example.unsplashapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unsplashapp.AuthInteractor

class MainActivityVmProvider(private val authInteractor: AuthInteractor) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(authInteractor) as T
    }
}