package com.example.testupstarts.di.main_screen

import android.view.LayoutInflater
import com.example.testupstarts.ui.main_screen.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface MainComponent {

    fun inject(activity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun bindInflater(inflater: LayoutInflater): Builder

        fun build(): MainComponent
    }
}