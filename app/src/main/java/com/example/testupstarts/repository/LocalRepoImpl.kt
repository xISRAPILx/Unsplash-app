package com.example.testupstarts.repository

import android.content.Context
import android.content.SharedPreferences

class LocalRepoImpl(context: Context): LocalRepository {

    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    override fun saveToken(token: String) {
        sharedPref.edit().putString(TOKEN_PREFS, token).apply()
    }

    override fun removeToken(token: String) {
        sharedPref.edit().remove(TOKEN_PREFS).apply()
    }


    override fun isGuest(): Boolean {
        return !sharedPref.contains(TOKEN_PREFS)
    }

    override fun getToken(): String? {
        return sharedPref.getString(TOKEN_PREFS,null)
    }


    companion object {
        const val PREFS = "prefs"
        const val TOKEN_PREFS = "access_token"
    }
}