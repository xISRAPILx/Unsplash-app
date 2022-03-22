package com.example.testupstarts.viewmodels.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.interactors.PhotoInteractor
import com.example.testupstarts.viewmodels.LoginUnsplashViewModel

class LoginUnsplashVmProvider(private val interactor: PhotoInteractor) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginUnsplashViewModel(interactor) as T
    }
}