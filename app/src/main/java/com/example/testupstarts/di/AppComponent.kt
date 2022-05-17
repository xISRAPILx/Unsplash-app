package com.example.testupstarts.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        InteractorModule::class]
)
interface AppComponent {
    fun mainComponentBuilder()
}