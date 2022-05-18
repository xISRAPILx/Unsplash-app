package com.example.testupstarts.di

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent =
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}