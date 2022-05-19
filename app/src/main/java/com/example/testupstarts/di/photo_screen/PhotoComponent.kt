package com.example.testupstarts.di.photo_screen

import android.view.LayoutInflater
import com.example.testupstarts.ui.photo_list_screen.PhotoFragment
import dagger.BindsInstance
import dagger.Subcomponent

@PhotoScope
@Subcomponent
interface PhotoComponent {

    fun inject(fragment: PhotoFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun bindInflater(inflater:LayoutInflater):Builder

        fun build(): PhotoComponent
    }
}