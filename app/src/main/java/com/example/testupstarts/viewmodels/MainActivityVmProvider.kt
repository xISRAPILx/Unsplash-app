package com.example.testupstarts.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.AuthInteractor

class MainActivityVmProvider(private val authInteractor: AuthInteractor) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(authInteractor) as T
    }
}