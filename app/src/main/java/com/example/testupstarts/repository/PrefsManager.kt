package com.example.testupstarts.repository

import android.content.Context
import android.content.SharedPreferences

class PrefsManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun isFavorite(id:String): Boolean {
        return prefs.contains(id)
    }

    fun addToFavorite(id: String) {
        prefs.edit()
            .putBoolean(id, true)
            .apply()
    }

    fun deleteFromFavorite(id: String) {
        prefs.edit()
            .remove(id)
            .apply()
    }

    companion object {
        private const val PREFS = "prefs"
    }
}