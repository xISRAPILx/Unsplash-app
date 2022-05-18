package com.example.testupstarts.ui.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.ui.auth_screen.AuthInteractor

class MainActivityVmProvider(private val authInteractor: AuthInteractor) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(authInteractor) as T
    }
}