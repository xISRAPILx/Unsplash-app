package com.example.testupstarts.ui.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.ui.photo_list_screen.PhotoInteractor

class LoginUnsplashVmProvider(private val interactor: PhotoInteractor) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginUnsplashViewModel(interactor) as T
    }
}