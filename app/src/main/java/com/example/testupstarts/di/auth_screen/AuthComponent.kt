package com.example.testupstarts.di.auth_screen

import android.view.LayoutInflater
import com.example.testupstarts.ui.auth_screen.AuthUnsplashFragment
import dagger.BindsInstance
import dagger.Subcomponent

@AuthScope
@Subcomponent
interface AuthComponent {
    fun inject(fragment: AuthUnsplashFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun bindInflater(inflater:LayoutInflater)

        fun build(): AuthComponent
    }
}