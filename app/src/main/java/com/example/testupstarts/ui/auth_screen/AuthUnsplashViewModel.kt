package com.example.testupstarts.ui.auth_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testupstarts.SingleLiveEvent
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