package com.example.unsplashapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashapp.AuthInteractor
import com.example.unsplashapp.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthUnsplashViewModel(private val authInteractor: AuthInteractor): ViewModel() {

    val tokenResult = SingleLiveEvent<Unit>()

    fun onAuthCodeExtracted(code: String) {
        authInteractor.saveAuthCode(code)
        loadToken(code)
    }

    private fun loadToken(code:String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val token = authInteractor.getTokenFromNetwork(code)
                authInteractor.saveToken(token.access_token)
                tokenResult.call()
            }
        }
    }
}