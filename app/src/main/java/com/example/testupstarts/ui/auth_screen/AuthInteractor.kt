package com.example.testupstarts.ui.auth_screen

import com.example.testupstarts.repository.PrefsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthInteractor(private val prefsRepo: PrefsRepository) {
    fun saveToken(token: String) {
        prefsRepo.saveToken(token)
    }

    fun isGuest(): Boolean {
        return prefsRepo.isGuest()
    }

    fun getTokenFromPrefs(): String? {
        return prefsRepo.getTokenFromPrefs()
    }

    suspend fun getTokenFromNetwork(code:String) = withContext(Dispatchers.IO) {
        val token = prefsRepo.getTokenFromNetwork(code)
        token
    }

    fun saveAuthCode(code: String) {
        prefsRepo.saveAuthCode(code)
    }
}