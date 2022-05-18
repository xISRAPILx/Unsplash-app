package com.example.testupstarts.di

import com.example.testupstarts.di.main_screen.MainComponent
import com.example.testupstarts.di.photo_screen.PhotoComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        InteractorModule::class]
)
interface AppComponent {
    fun mainComponentBuilder(): MainComponent.Builder
    fun photoComponentBuilder(): PhotoComponent.Builder
}