package com.example.testupstarts.di.card_screen

import android.view.LayoutInflater
import com.example.testupstarts.ui.card_screen.CardFragment
import dagger.BindsInstance
import dagger.Subcomponent

@CardScope
@Subcomponent
interface CardComponent {
    fun inject(fragment: CardFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun bindInflater(inflater: LayoutInflater)

        fun build(): CardComponent
    }
}