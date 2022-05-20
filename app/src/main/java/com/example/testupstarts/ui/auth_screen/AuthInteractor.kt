package com.example.testupstarts.ui.auth_screen

import com.example.testupstarts.repository.PrefsRepository
import com.example.testupstarts.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val prefsRepo: PrefsRepository,
    private val tokenRepo: TokenRepository
) {
    fun saveToken(token: String) {
        prefsRepo.saveToken(token)
    }

    fun isGuest(): Boolean {
        return prefsRepo.isGuest()
    }

    fun getToken(): String? {
        return prefsRepo.getToken()
    }

    suspend fun getTokenFromNetwork(code: String) = withContext(Dispatchers.IO) {
        val token = tokenRepo.getToken(code)
        token
    }

    fun saveAuthCode(code: String) {
        prefsRepo.saveAuthCode(code)
    }
}