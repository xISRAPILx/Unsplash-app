package com.example.testupstarts.repository

interface LocalRepository {
    fun saveToken(token:String)
    fun removeToken(token:String)
    fun isGuest():Boolean
    fun getTokenFromPrefs(): String?
    fun getAuthCode(): String?
    fun saveAuthCode(code: String)
    suspend fun getTokenFromNetwork(code:String): AuthToken
}