package com.example.testupstarts

import android.app.Application
import com.example.testupstarts.di.AppComponent
import com.example.testupstarts.di.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().bindsApplication(this).build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}