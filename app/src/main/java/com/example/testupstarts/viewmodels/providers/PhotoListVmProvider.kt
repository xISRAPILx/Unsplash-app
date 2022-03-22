package com.example.testupstarts.viewmodels.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.interactors.AuthInteractor
import com.example.testupstarts.interactors.PhotoInteractor
import com.example.testupstarts.viewmodels.PhotoListViewModel

class PhotoListVmProvider(
    private val authInteractor: AuthInteractor,
    private val photoInteractor: PhotoInteractor
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotoListViewModel(authInteractor, photoInteractor) as T
    }

}