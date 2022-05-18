package com.example.testupstarts.di.login_screen

import android.view.LayoutInflater
import com.example.testupstarts.ui.login_screen.LoginUnsplashFragment
import dagger.BindsInstance
import dagger.Subcomponent

@LoginScope
@Subcomponent
interface LoginComponent {
    fun inject(fragment: LoginUnsplashFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun bindsInflater(inflater: LayoutInflater)

        fun build(): LoginComponent
    }
}