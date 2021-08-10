package com.example.testupstarts.viewmodels

import androidx.lifecycle.ViewModel
import com.example.testupstarts.AuthInteractor

class AuthUnsplashViewModel(private val authInteractor: AuthInteractor): ViewModel() {

    fun onTokenExtracted(token: String) {
        authInteractor.saveToken(token)
    }
}