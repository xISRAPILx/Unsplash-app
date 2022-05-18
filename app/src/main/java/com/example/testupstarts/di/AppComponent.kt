package com.example.testupstarts.di

import android.content.Context
import com.example.testupstarts.di.auth_screen.AuthComponent
import com.example.testupstarts.di.card_screen.CardComponent
import com.example.testupstarts.di.login_screen.LoginComponent
import com.example.testupstarts.di.main_screen.MainComponent
import com.example.testupstarts.di.photo_screen.PhotoComponent
import dagger.BindsInstance
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
    fun authComponentBuilder(): AuthComponent.Builder
    fun cardComponentBuilder(): CardComponent.Builder
    fun loginComponentBuilder(): LoginComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindsApplication(context: Context): Builder

        fun build(): AppComponent
    }
}