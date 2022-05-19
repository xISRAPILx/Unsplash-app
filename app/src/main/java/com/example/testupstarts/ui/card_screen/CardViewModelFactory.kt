package com.example.testupstarts.ui.card_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.di.main_screen.MainScope
import com.example.testupstarts.ui.photo_list_screen.PhotoInteractor
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class CardViewModelFactory @AssistedInject constructor(private val photoInteractor: PhotoInteractor) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == CardViewModel::class.java)
        return CardViewModel(photoInteractor) as T
    }

    @AssistedFactory
    @MainScope
    interface Factory {
        fun create(): CardViewModelFactory
    }
}