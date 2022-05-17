package com.example.testupstarts.repository

import android.content.SharedPreferences
import com.example.testupstarts.repository.models.AuthToken

class LocalRepoImpl(private val sharedPref:SharedPreferences, private val apiAuth: ApiAuth) : LocalRepository {

    override fun saveToken(token: String) {
        sharedPref.edit().putString(TOKEN_PREFS, token).apply()
    }

    override fun removeToken(token: String) {
        sharedPref.edit().remove(TOKEN_PREFS).apply()
    }

    override fun isGuest(): Boolean {
        return !sharedPref.contains(TOKEN_PREFS)
    }

    override fun getTokenFromPrefs(): String? {
        return sharedPref.getString(TOKEN_PREFS, null)
    }

    override fun getAuthCode(): String? {
        return sharedPref.getString(AUTH_CODE, null)
    }

    override fun saveAuthCode(code: String) {
        sharedPref.edit().putString(AUTH_CODE, code).apply()
    }

    override suspend fun getTokenFromNetwork(code:String): AuthToken {
        return apiAuth.getToken(code)
    }

    companion object {
        const val PREFS = "prefs"
        const val TOKEN_PREFS = "access_token"
        const val AUTH_CODE = "auth_code"
    }
}