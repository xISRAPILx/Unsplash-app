package com.example.testupstarts.ui.auth_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AuthUnsplashVmProvider(private val authInteractor: AuthInteractor) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthUnsplashViewModel(authInteractor) as T
    }
}