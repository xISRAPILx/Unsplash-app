package com.example.testupstarts.di

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().bindsApplication(this).build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}