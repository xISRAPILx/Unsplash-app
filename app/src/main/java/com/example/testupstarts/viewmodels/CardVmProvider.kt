package com.example.testupstarts.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.PhotoInteractor

class CardVmProvider(private val interactor: PhotoInteractor) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CardViewModel(interactor) as T
    }
}