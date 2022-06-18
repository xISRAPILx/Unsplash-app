package com.example.testupstarts.ui.auth_screen

import com.example.testupstarts.repository.PrefsRepository
import com.example.testupstarts.repository.TokenRepository
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val prefsRepo: PrefsRepository,
    private val tokenRepo: TokenRepository
) {
    fun saveToken(token: String) {
        prefsRepo.saveToken(token)
    }

    fun isLogged(): Boolean {
        return prefsRepo.isLogged()
    }

    fun getToken(): String? {
        return prefsRepo.getToken()
    }

    suspend fun getTokenFromNetwork(code: String) = tokenRepo.getToken(code)

    fun saveAuthCode(code: String) {
        prefsRepo.saveAuthCode(code)
    }
}