package com.example.testupstarts.interactors

import com.example.testupstarts.repository.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthInteractor(private val localRepo: LocalRepository) {
    fun saveToken(token: String) {
        localRepo.saveToken(token)
    }

    fun isGuest(): Boolean {
        return localRepo.isGuest()
    }

    fun getTokenFromPrefs(): String? {
        return localRepo.getTokenFromPrefs()
    }

    suspend fun getTokenFromNetwork(code:String) = withContext(Dispatchers.IO) {
        val token = localRepo.getTokenFromNetwork(code)
        token
    }

    fun saveAuthCode(code: String) {
        localRepo.saveAuthCode(code)
    }
}