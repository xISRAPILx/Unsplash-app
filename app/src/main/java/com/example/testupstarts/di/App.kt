package com.example.testupstarts.di

import android.app.Application

class App(): Application() {
    private lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        synchronized(this) {
            instance = this
            appContainer = AppContainer(applicationContext)
        }
    }

    fun getAppContainer() = appContainer

    companion object {
        @Volatile
        lateinit var instance: App
    }
}