package com.example.testupstarts.ui.photo_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.ui.auth_screen.AuthInteractor

class PhotoVmProvider(
    private val authInteractor: AuthInteractor,
    private val photoInteractor: PhotoInteractor
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(authInteractor, photoInteractor) as T
    }

}