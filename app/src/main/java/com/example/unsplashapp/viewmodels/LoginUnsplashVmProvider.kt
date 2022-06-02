package com.example.unsplashapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unsplashapp.PhotoInteractor

class LoginUnsplashVmProvider(private val interactor: PhotoInteractor) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginUnsplashViewModel(interactor) as T
    }
}