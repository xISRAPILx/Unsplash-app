package com.example.testupstarts.ui.auth_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.di.auth_screen.AuthScope
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AuthViewModelFactory @AssistedInject constructor(
    private val authInteractor: AuthInteractor
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == AuthUnsplashViewModel::class.java)
        return AuthUnsplashViewModel(authInteractor) as T
    }

    @AssistedFactory
    @AuthScope
    interface Factory {
        fun create(): AuthViewModelFactory
    }
}