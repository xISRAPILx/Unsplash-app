package com.example.unsplashapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unsplashapp.AuthInteractor
import com.example.unsplashapp.PhotoInteractor

class PhotoVmProvider(
    private val authInteractor: AuthInteractor,
    private val photoInteractor: PhotoInteractor
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(authInteractor, photoInteractor) as T
    }

}