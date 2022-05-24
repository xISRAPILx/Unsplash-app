package com.example.testupstarts.ui.auth_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testupstarts.SingleLiveEvent
import com.example.testupstarts.interactors.AuthInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthUnsplashViewModel @Inject constructor(private val authInteractor: AuthInteractor) :
    ViewModel() {

    val tokenResult = SingleLiveEvent<Unit>()

    fun onAuthCodeExtracted(code: String) {
        authInteractor.saveAuthCode(code)
        loadToken(code)
    }

    private fun loadToken(code: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val token = authInteractor.getTokenFromNetwork(code)
                token.accessToken?.let { authInteractor.saveToken(it) }
                tokenResult.call()
            }
        }
    }
}