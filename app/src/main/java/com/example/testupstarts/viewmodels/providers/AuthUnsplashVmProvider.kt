package com.example.testupstarts.viewmodels.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.interactors.AuthInteractor
import com.example.testupstarts.viewmodels.AuthUnsplashViewModel

class AuthUnsplashVmProvider(private val authInteractor: AuthInteractor) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthUnsplashViewModel(authInteractor) as T
    }
}