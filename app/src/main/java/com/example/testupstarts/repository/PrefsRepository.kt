package com.example.testupstarts.repository

interface PrefsRepository {
    fun saveToken(token:String)
    fun removeToken(token:String)
    fun isGuest():Boolean
    fun getTokenFromPrefs(): String?
    fun getAuthCode(): String?
    fun saveAuthCode(code: String)
}