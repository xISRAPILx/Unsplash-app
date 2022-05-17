package com.example.testupstarts.repository

import com.example.testupstarts.repository.models.AuthToken

interface LocalRepository {
    fun saveToken(token:String)
    fun removeToken(token:String)
    fun isGuest():Boolean
    fun getTokenFromPrefs(): String?
    fun getAuthCode(): String?
    fun saveAuthCode(code: String)
    suspend fun getTokenFromNetwork(code:String): AuthToken
}