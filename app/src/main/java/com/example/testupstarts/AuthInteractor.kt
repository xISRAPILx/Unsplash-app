package com.example.testupstarts

import com.example.testupstarts.repository.LocalRepository

class AuthInteractor(private val localRepo: LocalRepository) {
    fun saveToken(token: String) {
        localRepo.saveToken(token)
    }

    fun isGuest(): Boolean {
        return localRepo.isGuest()
    }

    fun getToken(): String? {
        return  localRepo.getToken()
    }
}