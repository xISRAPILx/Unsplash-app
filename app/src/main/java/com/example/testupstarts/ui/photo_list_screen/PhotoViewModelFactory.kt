package com.example.testupstarts.ui.photo_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.di.main_screen.MainScope
import com.example.testupstarts.ui.auth_screen.AuthInteractor
import com.example.testupstarts.ui.main_screen.MainActivityViewModelFactory
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PhotoViewModelFactory @AssistedInject constructor(
    private val authInteractor: AuthInteractor,
    private val photoInteractor: PhotoInteractor
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == PhotoViewModel::class.java)
        return PhotoViewModel(authInteractor, photoInteractor) as T
    }

    @AssistedFactory
    @MainScope
    interface Factory {
        fun create(): MainActivityViewModelFactory
    }
}