package com.example.testupstarts.ui.auth_screen

import com.example.testupstarts.repository.TokenRepoImpl
import com.example.testupstarts.repository.PrefsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthInteractor(
    private val prefsRepo: PrefsRepository,
    private val networkRepo: TokenRepoImpl
) {
    fun saveToken(token: String) {
        prefsRepo.saveToken(token)
    }

    fun isGuest(): Boolean {
        return prefsRepo.isGuest()
    }

    fun getToken(): String? {
        return prefsRepo.getTokenFromPrefs()
    }

    suspend fun getTokenFromNetwork(code: String) = withContext(Dispatchers.IO) {
        val token = networkRepo.getToken(code)
        token
    }

    fun saveAuthCode(code: String) {
        prefsRepo.saveAuthCode(code)
    }
}